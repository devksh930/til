/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.simplecoapclient;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;

/**
 *
 * @author yjkim
 */
public class ObserveCoAPClient implements CoAPClient{
    private CoapClient client = null;

    @Override
    public void run(String hostName) {
        if (hostName == null) hostName = "localhost";
        
        String uri = "coap://" + hostName + ":5683" + "/hello/count";
        client = new CoapClient(uri);
        CoapObserveRelation relation = client.observe(new CoapHandler() {
            @Override
            public void onLoad(CoapResponse cr) {
                System.out.println("Changed value of count: " + cr.getResponseText());
            }

            @Override
            public void onError() {
                System.out.println("Error occured at observing the change of count...");
            }
        });
        
        Thread quit = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Press enter to exit: ");
                Scanner input = new Scanner(System.in);
                input.nextLine();
                relation.proactiveCancel();
            }
        });
        
        try {
            quit.start();
            quit.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ObserveCoAPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
