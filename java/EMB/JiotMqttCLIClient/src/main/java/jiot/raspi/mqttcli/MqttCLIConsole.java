/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.mqttcli;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.StringReader;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class MqttCLIConsole {
    public static final String TOPIC_PREFIX = "jiot/mqtt/thing/";
    public static final String TOPIC_COMMAND = TOPIC_PREFIX + "%s/%s/command";
    public static final String TOPIC_RESULT = TOPIC_PREFIX + "%s/result";
    public static final String TOPIC_BROADCAST = TOPIC_PREFIX + "%s/broadcast";

    private String url;
    private String clientId;
    private MqttClient client;
    private String commandTopic;
    private String resultTopic;
    private String broadcastTopic;
    private BigDataHandler bdHandler;

    public MqttCLIConsole(String clientId, String handlerId, BigDataHandler handler) 
            throws MqttException {
        this.clientId = clientId;
        this.bdHandler = handler;

        url = System.getProperty("mqtt.server", "tcp://127.0.0.1:1883");
        System.out.println("MQTT Broker url: " + url);
        
        commandTopic = String.format(TOPIC_COMMAND, clientId, handlerId);
        resultTopic = String.format(TOPIC_RESULT, clientId);
        broadcastTopic = String.format(TOPIC_BROADCAST, handlerId);
        
        System.out.println("Command topic : " + commandTopic);
        System.out.println("Result topic : " + resultTopic);
        System.out.println("Broadcast topic : " + broadcastTopic);
        
        String tmpDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);

        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);

        client = new MqttClient(url, clientId, dataStore);
        
        client.setCallback(new MqttCallback() {
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                if(topic.endsWith("broadcast")) {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObj = parser.parse(message.toString()).getAsJsonObject();
                    String broadcastType = jsonObj.get("type").getAsString();
                    if(broadcastType.equals(ChangeOfValue.TYPE)){
                        ChangeOfValue cov = new ChangeOfValue(jsonObj);
                        bdHandler.saveBigData(cov);
                    } 
                    else {
                        System.out.println("Received a breoadcast: " + message);
                    }
                }
                else {
                    System.out.println("Result: " + message);
                    displayPrompt();
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                try {
                    Logger.getLogger(MqttCLIConsole.class.getName())
                            .log(Level.INFO, String.format(
                                "Delivered - [%s] message: %s ",
                                new Date(), token.getMessage()));
                } catch (MqttException ignored) {
                }
            }

            @Override
            public void connectionLost(Throwable cause) {
                Logger.getLogger(MqttCLIConsole.class.getName())
                        .log(Level.SEVERE, null, cause);
                System.exit(-1);
            }
        });

        client.connect(conOpt);
        client.subscribe(resultTopic);
        client.subscribe(broadcastTopic);
    }

    public void publish(String payload, int qos) throws MqttException {
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(qos);
        client.publish(commandTopic, message);
    }

    public void close() throws MqttException {
        if (client != null) {
            client.disconnect();
            client.close();
            client = null;
        }
    }
    
    private void displayPrompt() {
        System.out.print("input command or 'q'(quit): ");
    }
}
