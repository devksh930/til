/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.simplecoapclient;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.elements.exception.ConnectorException;

/**
 *
 * @author yjkim
 */
public class HelloCoAPClient implements CoAPClient {
    private CoapClient client = null;

    @Override
    public void run(String hostName) {
        client = new CoapClient();
        
        CoapResponse response = null;
        String uri = null;
        
        Scanner input = new Scanner(System.in);
        System.out.println(">>> Enter URI path(or 'q'): ");
        for (String line = input.nextLine(); !line.equals("q"); line = input.nextLine()) {
            try {
                // send GET request to CoAP server
                uri = "coap://" + hostName + ":5683" + line + "?text=everybody";
                client.setURI(uri);
                response = client.get();
                if (response != null) {
                    write("code: " + response.getCode());
                    write("options: " + response.getOptions());
                    write("payload: " + Utils.toHexString(response.getPayload()));
                    write("text: " + response.getResponseText());
                    write("advanced: " + System.lineSeparator() + Utils.prettyPrint(response));
                }
                
                // send POST request to CoAP server
                uri = "coap://" + hostName + ":5683" + line;
                client.setURI(uri);
                response = client.post("everybody", MediaTypeRegistry.TEXT_PLAIN);
                if (response != null) {
                    write("Post: " + response.getResponseText());
                }
            } catch (ConnectorException ex) {
                Logger.getLogger(HelloCoAPClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HelloCoAPClient.class.getName()).log(Level.SEVERE, null, ex);
            }           

            System.out.println(">>> Enter URI path(or 'q'): ");
        }
    }
    
    private void write(String msg) {
        System.out.println(msg);
    }
    
}
