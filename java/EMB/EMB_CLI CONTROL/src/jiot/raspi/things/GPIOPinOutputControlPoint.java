/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.things;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.dio.DeviceManager;
import jdk.dio.gpio.GPIOPin;

/**
 *
 * @author yjkim
 */
public class GPIOPinOutputControlPoint extends OutputControlPoint {
    private int pinId;
    private GPIOPin pinDev = null;

    public GPIOPinOutputControlPoint(int pinId) {
        super();
        this.pinId = pinId;
    }
    
    @Override
    public void open() {
        try {
            pinDev = DeviceManager.open(pinId, GPIOPin.class);
            setName("GPIO" + pinId);
        } catch (IOException ex) {
            Logger.getLogger(GPIOPinControlPoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() {
        if (isEnabled()) {
            try {
                pinDev.close();
                pinDev = null;
            } catch (IOException ex) {
                Logger.getLogger(GPIOPinControlPoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean isEnabled() {
        return (pinDev != null && pinDev.isOpen());
    }

    @Override
    public Type getType() {
        return Type.DO;
    }

    @Override
    public void setPresentValue(int value) {
        int oldValue = getPresentValue();
        
        if (writeValue(value) && oldValue != getPresentValue()) {
            fireChanged();
        }
    }
    
    private boolean writeValue(int value) {
        boolean success = false;
        try {
            pinDev.setValue(value == 1);
            presentValue.set(value);
            success = true;
        } catch (IOException ex) {
            Logger.getLogger(GPIOPinOutputControlPoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }
    
}
