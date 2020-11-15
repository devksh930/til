/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.simplecoapserver;

import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP.Type;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 *
 * @author yjkim
 */
public class SimpleCoAPServer {
    
    public static void main(String[] args) {
        CoapServer server = new CoapServer();

        CoapResource resource = new CoapResource("hello") {     // "/hello" resource
            @Override
            public void handlePOST(CoapExchange exchange) {
                String text = exchange.getRequestText();
                System.out.println("'" + getName() + "' called by POST method");
                exchange.respond("Hello, " + text + "!");
            }

            @Override
            public void handleGET(CoapExchange exchange) {
                String text = exchange.getRequestOptions().getUriQuery().get(0);
                System.out.println("'" + getName() + "' called by GET method");
                exchange.respond("Hello, " + text.substring(text.indexOf("=") + 1) + "!");
            }      
        };

        // add "/hello/world" resource
        resource.add(new CoapResource("world") {        // "/hello/world" resource
            @Override
            public void handlePOST(CoapExchange exchange) {
                String text = exchange.getRequestText();
                System.out.println("'" + getName() + "' called by POST method");
                exchange.respond("Hello World!, " + text + "!");
            }

            @Override
            public void handleGET(CoapExchange exchange) {
                String text = exchange.getRequestOptions().getUriQuery().get(0);
                System.out.println("'" + getName() + "' called by GET method");
                exchange.respond("Hello World!, " + text.substring(text.indexOf("=") + 1) + "!");
            }      
        });
        
        // add "/hello/count" observable resource
        resource.add(new CoapResource("count") {
            private int count = 0;
            
            private CoapResource initialize() {
                setObservable(true);
                setObserveType(Type.CON);
                
                start();
                
                return this;
            }
            
            private void increment() {
                count++;
            }
            
            private void start() {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() { 
                    @Override
                    public void run() {
                        increment();
                        changed();
                    }              
                }, 1000, 1000);
            }
            
            @Override
            public void handleGET(CoapExchange exchange) {
                exchange.respond(String.valueOf(count));
            }
            
        }.initialize());

        server.add(resource);
        server.start();
    }
}
