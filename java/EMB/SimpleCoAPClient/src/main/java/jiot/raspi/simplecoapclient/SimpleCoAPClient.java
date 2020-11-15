/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.simplecoapclient;

/**
 *
 * @author yjkim
 */
public class SimpleCoAPClient {

    public static void main(String[] args) {
        CoAPClient client = null;
        
//        client = new HelloCoAPClient();
//        client = new AsyncHelloCoAPClient();
//        client = new ObserveCoAPClient();
        client = new DiscoveryCoAPClient();
        client.run(args[0]);
    }
}
