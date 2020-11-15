/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.iotcliapp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jiot.raspi.cli.CLIConsole;
import jiot.raspi.things.ControlPointContainer;

/**
 *
 * @author yjkim
 */
public class IoTCliApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControlPointContainer cpContainer = ControlPointContainer.getInstance();
        cpContainer.start();
        
        try {
            CLIConsole console = new CLIConsole(null);
            
            cpContainer.getControlPoints().forEach((cp) -> { 
                cp.addObserver(console);
            });
            
            console.displayChangeLog(true);
            console.run();          
        } catch (IOException ex) {
            Logger.getLogger(IoTCliApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cpContainer.stop();
    }
    
}
