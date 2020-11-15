package jiot.raspi.simplemqttpublisher;

import java.util.Date;
import java.util.Scanner;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yjkim
 */
public class SimpleMqttPublisher {
    private final static String CLIENT_ID = "simplemqttpublisher";
    private final static String TOPIC = "Sports";

    private MqttClient client;
    private String uri;

    public SimpleMqttPublisher() {
        uri = System.getProperty("mqtt.server");
        System.out.println("uri = " + uri);
        String tmpDir = System.getProperty("java.io.tmpdir");
        System.out.println("tmpDir = " + tmpDir);

        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
        try {
            MqttConnectOptions connOpt = new MqttConnectOptions();
            connOpt.setCleanSession(true);

            client = new MqttClient(uri, CLIENT_ID, dataStore);

            client.setCallback(new MqttCallback() {
                @Override
                public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    try {
                        System.out.println(String.format("Delivered - [%s] message : %s ", 
                                new Date(), token.getMessage()));
                    } catch (MqttException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void connectionLost(Throwable arg0) {
                    System.exit(-1);
                }
            });
			
            client.connect(connOpt);
        } catch (MqttException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
	
    public void publish(String payload, int qos) throws MqttPersistenceException, MqttException {
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(qos);
        client.publish(TOPIC, message);
    }
	
    public void close() {
        if (client != null) {
            try {
                client.disconnect();
                client.close();
                client = null;
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
	
    public static void main(String[] args) {
        SimpleMqttPublisher publisher = new SimpleMqttPublisher();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a message (or 'q' to exit): ");
        for (String msg = scanner.nextLine(); !msg.equals("q"); msg = scanner.nextLine()) {
            System.out.print("Enter a QoS level(0, 1, 2): ");
            int qos = Integer.parseInt(scanner.nextLine());
            System.out.println(">>> Publishing '" + msg + "' with qos=" + qos + " ...");
            try {
                publisher.publish(msg, qos);
            } catch (MqttPersistenceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.print("Enter a message (or 'q' to exit): ");
        }
		
        System.out.println("MQTT Publisher is closing...");
        publisher.close();
        scanner.close();
    }    
}
