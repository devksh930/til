/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.simplecoapclient;

import java.util.Scanner;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

/**
 *
 * @author yjkim
 */
public class AsyncHelloCoAPClient implements CoAPClient {
    private CoapClient client = null;

    @Override
    public void run(String hostName) {
        client = new CoapClient("coap://" + hostName + ":5683/hello?text=Everybody");
        
        client.get(new CoapHandler() { 
            @Override
            public void onLoad(CoapResponse cr) {
                System.out.println("Async. GET: " + cr.getResponseText());
            }

            @Override
            public void onError() {
                System.out.println("Cannot get the response asynchronously...");
            }
        });
        
        client.post(new CoapHandler() { 
            @Override
            public void onLoad(CoapResponse cr) {
                System.out.println("Async. POST: " + cr.getResponseText());
            }

            @Override
            public void onError() {
                System.out.println("Cannot get the response asynchronously...");
            }
        }, "Everybody", MediaTypeRegistry.TEXT_PLAIN);
        
        System.out.println("Press enter to exit: ");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }
    
}
