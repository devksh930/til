/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.mqttcli;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author yjkim
 */
public class MqttCLIClient {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Input ID: ");
        String clientId = input.nextLine();
//        System.out.print("Input the IP Address of thing: ");
//        String handlerId = input.nextLine().replace('.', '_');
        System.out.print("Input the CLI handler ID: ");
        String handlerId = input.nextLine();
        System.out.println("MqttConsole connecting...");

        try {
            BigDataHandler bdHandler = new BigDataHandler();
            MqttCLIConsole console = 
                    new MqttCLIConsole(clientId, handlerId, bdHandler);
            System.out.print("input command or 'q'(quit): ");
            for (String line = input.nextLine();
                    !line.equals("q");
                    line = input.nextLine()) {
                
                if(line.trim().length() == 0)
                    continue;
                
                if (line.equals("display")) {
                    bdHandler.displayBigData();
                    System.out.print("input command or 'q'(quit): ");
                }
                else if (line.equals("clear")) {
                    bdHandler.clearBigData();
                    System.out.print("input command or 'q'(quit): ");
                }
                else {
                    console.publish(line, 1);
                    System.out.print("Waiting result....");
                }
            }
            console.close();
        } catch (MqttException ex) {
            Logger.getLogger(MqttCLIClient.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }    
}
