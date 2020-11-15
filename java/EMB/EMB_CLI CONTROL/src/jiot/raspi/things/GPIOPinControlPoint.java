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
import jdk.dio.gpio.PinEvent;
import jdk.dio.gpio.PinListener;

/**
 *
 * @author yjkim
 */
public class GPIOPinControlPoint extends ControlPoint {
    private int pinId;
    private GPIOPin pinDev = null;

    public GPIOPinControlPoint(int pinId) {
        super();
        this.pinId = pinId;
    }
    
    @Override
    public void open() {
        try {
            pinDev = DeviceManager.open(pinId, GPIOPin.class);
            presentValue.set(pinDev.getValue() ? 1 : 0);
            setName("GPIO" + pinId);

            pinDev.setInputListener(new PinListener() {
                @Override
                public void valueChanged(PinEvent pe) {
                    try {
                        int oldValue = presentValue.get();
                        int newValue = pinDev.getValue() ? 1 : 0;
                        presentValue.set(newValue);
                        if (oldValue != newValue) {
                            fireChanged();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(GPIOPinControlPoint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
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
        return Type.DI;
    }
    
}
