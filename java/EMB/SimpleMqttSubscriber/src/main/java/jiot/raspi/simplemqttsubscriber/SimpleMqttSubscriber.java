/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.simplemqttsubscriber;

import java.util.Date;
import java.util.Scanner;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

/**
 *
 * @author yjkim
 */
public class SimpleMqttSubscriber {
    private final static String CLIENT_ID = "simplemqttsubscriber";
    private final static String TOPIC = "Sports";

    private MqttClient client;
    private String uri;

    public SimpleMqttSubscriber() {
        uri = System.getProperty("mqttt.server", "tcp://localhost:1883");
        String tmpDir = System.getProperty("java.io.tmpdir");		
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);

        try {
            MqttConnectOptions connOpt = new MqttConnectOptions();
            connOpt.setCleanSession(true);

            client = new MqttClient(uri, CLIENT_ID, dataStore);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    cause.printStackTrace();
                    System.exit(-1);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken arg0) {
                }

                @Override
                public void messageArrived(String topic, MqttMessage msg) throws Exception {
                    System.out.println(String.format("Arrived - [%s] message: %s", new Date(), msg));
                }
            });
			
            client.connect(connOpt);
        } catch (MqttException ex) {
            ex.printStackTrace();
        }		
    }
	
    public void subscribe() throws MqttException {
            client.subscribe(TOPIC);
    }

    public void close() {
        try {
            client.disconnect();
            client.close();
            client = null;
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimpleMqttSubscriber subscriber = new SimpleMqttSubscriber();
        Scanner scanner = new Scanner(System.in);

        try {
            subscriber.subscribe();

            System.out.println("Enter any key to quit...");
            scanner.nextLine();
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
        System.out.println("MQTT Subscriber is closing...");
        scanner.close();
        subscriber.close();
    }    
}
