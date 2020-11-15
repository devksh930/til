/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.things;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import jiot.raspi.uart_dev.UARTCommCommand;

/**
 *
 * @author yjkim
 */
public class ControlPointContainer {
    
    private static final AtomicReference<ControlPointContainer> instance =
            new AtomicReference<ControlPointContainer>();
    
    public static ControlPointContainer getInstance() {
        if (instance.get() == null)
            instance.set(new ControlPointContainer());
        return instance.get();
    }
    
    private final Map<Integer, ControlPoint>  controlPoints = 
            new HashMap<Integer, ControlPoint>();
    
    protected ControlPointContainer() {
        
    }
    
    public void start() {
        createAndOpenControlPoints();
    }
    
    public void stop() {
        controlPoints.values().forEach((cp) -> {
            cp.close();
        });
        controlPoints.clear();
        ControlPoint.shutdownExecutor();
    }
    
    public Collection<ControlPoint> getControlPoints() {
        return Collections.unmodifiableCollection(controlPoints.values());
    }
    
    public ControlPoint getControlPoint(int pointId) {
        return controlPoints.get(pointId);
    }
    
    public ControlPoint getControlPointByName(String name) {
        ControlPoint point = null;
        
        for (ControlPoint cp : controlPoints.values()) {
            if (cp.getName().equals(name)) {
                point = cp;
                break;
            }
        }
        
        return point;
    }
    
    public void addControlPoint(ControlPoint cp) {
        controlPoints.put(cp.getId(), cp);
        cp.open();
    }
    
    private void createAndOpenControlPoints() {
        addControlPoint(new GPIOPinOutputControlPoint(17));      // LED1 id=0
        addControlPoint(new GPIOPinOutputControlPoint(27));      // LED2 id=1
        addControlPoint(new GPIOPinOutputControlPoint(22));      // LED3 id=2
        
        addControlPoint(new GPIOPinControlPoint(23));            // Button1 id=3
        addControlPoint(new GPIOPinControlPoint(24));            // Button2 id=4
        addControlPoint(new GPIOPinControlPoint(25));            // PIR Motion Sensor id=5
        
        int[] pins = {5, 6};
        addControlPoint(new BusOutControlPoint(pins));           // Vantilation Fan id=6

        addControlPoint(new AnalogInputControlPoint(0));         // CDR sensor id=7

        addControlPoint(new SerialInControlPoint(UARTCommCommand.GET_TMP.cmd, "T"));     // Temperature sensor id=8
        addControlPoint(new SerialInControlPoint(UARTCommCommand.GET_HMD.cmd, "H"));     // Humidity sensor id=9
        addControlPoint(new SerialInControlPoint(UARTCommCommand.GET_ACK.cmd, "OK"));    // Acknowledgement request id=10
    }
    
    public enum ControlPointID {
        LED1(0),
        LED2(1),
        LED3(2),
        BTN1(3),
        BTN2(4),
        PIR(5),
        FAN(6),
        CDR(7),
        TMP(8),
        HMD(9),
        ACK(10);
        
        private int id;
        
        private ControlPointID(int id) {
            this.id = id;
        }
        
        public int getId() {
            return this.id;
        }    
    }
}
