/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.things;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import jiot.raspi.spi_dev.SPIRPI;
import jiot.raspi.spi_dev.drivers.MCP3208Device;

/**
 *
 * @author yjkim
 */
public class AnalogInputControlPoint extends ControlPoint {
    private static AtomicReference<MCP3208Device> adcDevRef =
            new AtomicReference<MCP3208Device>();
    
    private static MCP3208Device getAdcDevice() {
        try {
            if (adcDevRef.get() == null) 
                adcDevRef.set(new MCP3208Device(SPIRPI.CE1));
        } catch (IOException ex) {
            Logger.getLogger(AnalogInputControlPoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adcDevRef.get();
    }
    
    private static final AtomicInteger OPEN_COUNT = new AtomicInteger(0);
    private int channel;
    private Future pollingFuture = null;
    
    public AnalogInputControlPoint(int channel) {
        super();
        this.channel = channel;
    }

    public int getChannel() {
        return channel;
    }
    
    public int read() {
        int value = -1;
        
        try {
            value = getAdcDevice().analogRead(channel);
            presentValue.set(value);
        } catch (IOException ex) {
            Logger.getLogger(AnalogInputControlPoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return value;
    }
    
    public void open(boolean polling) {
        OPEN_COUNT.incrementAndGet();
        
        if (polling) {
            pollingFuture = POLLING.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    try {
                        int oldValue = presentValue.get();
                        int newValue = getAdcDevice().analogRead(channel);
                        presentValue.set(newValue);
//                        if (oldValue != newValue) 
                        if (Math.abs(oldValue - newValue) > 20) 
                            fireChanged();
                    } catch (IOException ex) {
                        Logger.getLogger(AnalogInputControlPoint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
        }
        
        setName("AI_Channel_" + channel);
    }

    @Override
    public void open() {
        this.open(true);
    }

    @Override
    public void close() {
        int ref_count = OPEN_COUNT.decrementAndGet();
        if (ref_count >= 0) {
            if (pollingFuture != null) {
                pollingFuture.cancel(false);
                pollingFuture = null;
            }
            if (ref_count == 0) {
                getAdcDevice().close();
                adcDevRef.set(null);
            }
        }
        else { 
            OPEN_COUNT.set(0);
        }
    }

    @Override
    public boolean isEnabled() {
        return getAdcDevice().device.isOpen();
    }

    @Override
    public Type getType() {
        return Type.AI;
    }
    
}
