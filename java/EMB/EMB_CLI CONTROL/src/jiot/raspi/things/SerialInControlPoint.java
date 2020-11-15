/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jiot.raspi.things;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jiot.raspi.uart_dev.UARTRPI;

/**
 *
 * @author yjkim
 */
public class SerialInControlPoint extends ControlPoint implements FloatInputSupport {
    private UARTRPI uartDev = null;
    private String command;
    private String responsePrefix;
    private boolean isOpen = false;

    class SerialInDataListener implements UARTRPI.UARTDataListener{

        @Override
        public boolean processReceivedData(String data) {
            boolean processed = false;
            float value = 1;
            String[] tokens = data.split("=|\\n");
            if (tokens[0].equals(responsePrefix)) {
                if (!responsePrefix.equals("OK")) {
                    value = Float.parseFloat(tokens[1]);
                }
                presentValue.set(Float.floatToIntBits(value));
                processed = true;
            }
            return processed;
        }
        
    }
    
    public SerialInControlPoint(String command, String prefix) {
        super();
        this.command = command;
        this.responsePrefix = prefix;
    }
    
    @Override
    public void open() {            
        try {
            uartDev = UARTRPI.getInstance();
            uartDev.addDataListener(command, new SerialInDataListener());
            isOpen = true;
            setName("SerialIn_" + command.split("\n")[0]);
        } catch (IOException ex) {
            Logger.getLogger(SerialInControlPoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() {
        if (isEnabled()) {
            try {
                uartDev.removeDataListener(command);
                uartDev.close();
                isOpen = false;
            } catch (IOException ex) {
                Logger.getLogger(SerialInControlPoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean isEnabled() {
        return (uartDev != null && isOpen);
    }

    @Override
    public Type getType() {
        return Type.SI;
    }

    @Override
    public float getFloatValue() {
        float value = -1;
        try {
            if (uartDev.sendSync(command)) {
                value = Float.intBitsToFloat(presentValue.get());
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(SerialInControlPoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

}
