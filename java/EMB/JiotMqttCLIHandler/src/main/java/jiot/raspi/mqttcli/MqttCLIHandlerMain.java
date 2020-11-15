/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.mqttcli;

import java.util.logging.Level;
import java.util.logging.Logger;
import jiot.raspi.thing.ControlPoint;
import jiot.raspi.thing.ControlPointContainer;

/**
 *
 * @author yjkim
 */
public class MqttCLIHandlerMain {
    private static ControlPointContainer container = null;
    private static MqttCLIHandler cliHandler = null;
	
    public static void close() {
        if (container != null)  container.stop();
        if (cliHandler != null)  cliHandler.close();
    }
	
    public static void main(String[] args) {
        try {
            container = ControlPointContainer.getInstance();
            container.start();
			
            cliHandler = new MqttCLIHandler();
            for (ControlPoint cp : container.getControlPoints()) {
                cp.addObserver(cliHandler);
            }
			
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    System.out.println("Program is shutdowning...");
                    close();
                }
            });

            for(;;)
                Thread.sleep(1000);			 
        } catch (Exception ex) {
            Logger.getLogger(MqttCLIHandlerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
