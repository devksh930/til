/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.mqttechoreceiver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

/**
 *
 * @author yjkim
 */
public class MqttEchoReceiver {
    public static final String TOPIC_PREFIX = "jiot/mqtt/";
    public static final String TOPIC_ECHO = TOPIC_PREFIX + "+/echo";
    public static final String TOPIC_RESPONSE = TOPIC_PREFIX + "%s/response";

    private String uri;
    private String clientId;
    private MqttClient client;

    public MqttEchoReceiver() throws SocketException, MqttException, UnknownHostException {
        uri = System.getProperty("mqtt.server", "tcp://localhost:1883");
        System.out.println("MQTT Broker URI: " + uri);
        String ipAddress = getLocalIPAddress();		
        System.out.println("Used IP address: " + ipAddress);
        clientId = "echo" + ipAddress.replace('.', '_');
//        clientId = "mqttechoreceiver";

        String tmpDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);

        MqttConnectOptions connOpt = new MqttConnectOptions();
        connOpt.setCleanSession(true);

        client = new MqttClient(uri, clientId, dataStore);

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("Connection lost...");
                System.exit(-1);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                try {
                    System.out.printf("Delivered - [%s] message: %s\n", new Date(), token.getMessage().toString());
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void messageArrived(String topic, MqttMessage msg) throws Exception {
                String[] clientInfo = topic.substring(TOPIC_PREFIX.length()).split("/");                
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty("clientId", clientInfo[0]);
                jsonObj.addProperty("message", msg.toString());
                String echoMsg = (new Gson()).toJson(jsonObj);
                System.out.println("Publish a message '" + echoMsg + "' to " + clientInfo[0]);
                publish(clientInfo[0], echoMsg, 0);
            }
        });
		
        client.connect(connOpt);
    }
	
    public void publish(String clientId, String payload, int qos) throws MqttPersistenceException, MqttException {
        MqttTopic topic = client.getTopic(String.format(TOPIC_RESPONSE, clientId));
        topic.publish(payload.getBytes(), qos, false);
    }
	
    public void subscribe() throws MqttException {
        client.subscribe(TOPIC_ECHO);
    }
	
    public String getLocalIPAddress() throws SocketException, UnknownHostException {
        String ipAddress = null;
//        Enumeration<NetworkInterface> networkList = NetworkInterface.getNetworkInterfaces();
//        NetworkInterface ni;
//
//        while (networkList.hasMoreElements()) {
//            ni = networkList.nextElement();
//            if (!ni.getName().equals("enp3s0")) {
//                continue;
//            }
//
//            Enumeration<InetAddress> addresses = ni.getInetAddresses();
//            while (addresses.hasMoreElements()) {
//                ipAddress = addresses.nextElement().getHostAddress();
//                break;
//            }
//
//            if (ipAddress != null)  break;
//        }
        InetAddress localhost = InetAddress.getLocalHost();
        ipAddress = (localhost.getHostAddress()).trim();         

        return ipAddress;
    }
	
    public void close() {
        if (client != null) {
            try {
                client.disconnect();
                client.close();
                client = null;
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
	
    public static void main(String[] args) {
        Scanner scanner = null;
        MqttEchoReceiver receiver = null;

        try {
            receiver = new MqttEchoReceiver();
            receiver.subscribe();
			
//            Runtime.getRuntime().addShutdownHook(new Thread() {
//                @Override
//                public void run() {
//                        receiver.close();
//                }
//            });

            scanner = new Scanner(System.in);
            System.out.println("Enter any key to quit ...");
            scanner.nextLine();			
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (scanner != null)  scanner.close();
            if (receiver != null)  receiver.close();
        }
		
    }    
}
