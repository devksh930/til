/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.uart_dev.drivers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jiot.raspi.uart_dev.UARTCommCommand;
import jiot.raspi.uart_dev.UARTRPI;

/**
 *
 * @author yjkim
 */
public class SHT11Device {
    public static final String DEV_NAME = "SHT11";
    
    private UARTRPI uart = null;
    private double temperature = 0;
    private double humidity = 0;
    private boolean active = false;
    
    class SHT11DataListener implements UARTRPI.UARTDataListener{

        @Override
        public boolean processReceivedData(String data) {
            boolean processed = false;
            String[] tokens = data.split("=|\\n");
            if (tokens[0].equals("H")) {
                humidity = Double.parseDouble(tokens[1]);
                processed = true;
            }
            else if (tokens[0].equals("T")) {
                temperature = Double.parseDouble(tokens[1]);
                processed = true;
            }
            if (tokens[0].equals("OK")) {
                active = true;
                processed = true;
            }
            return processed;
        }
        
    }

    public SHT11Device() throws IOException {
        uart = UARTRPI.getInstance();
        uart.addDataListener(DEV_NAME, new SHT11DataListener());
    }
    
    public void close() throws IOException {
        if (uart != null) {
            uart.removeDataListener(DEV_NAME);
            uart.close();
        }
    }
    
    public boolean isActive() {
        try {
            UARTCommCommand.GET_ACK.send(uart);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(SHT11Device.class.getName()).log(Level.SEVERE, null, ex);
        }
        return active;
    }
    
    public double getTemperature() {
        try {
            UARTCommCommand.GET_TMP.send(uart);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(SHT11Device.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temperature;
    }    

    public double getHumidity() {
        try {
            UARTCommCommand.GET_HMD.send(uart);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(SHT11Device.class.getName()).log(Level.SEVERE, null, ex);
        }
        return humidity;
    }    

}
