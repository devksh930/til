/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.i2c_dev.drivers;

import java.io.IOException;
import jiot.raspi.i2c_dev.I2CRPI;
import jiot.raspi.i2c_dev.PCF8591;

/**
 *
 * @author yjkim
 */
public class PCF8591Device extends I2CRPI {
    private static final int PCF8591_ADDR = 0x48;
    
    public PCF8591Device() throws IOException {
        super(PCF8591_ADDR);
        device.write(0x00);
    }
    
    public int analogRead(int ainPin) {
        int value = 0;
        switch (ainPin) {
            case 0:
                value = PCF8591.AIN0.read(device);  break;
            case 1:
                value = PCF8591.AIN1.read(device);  break;
            case 2:
                value = PCF8591.AIN2.read(device);  break;
            case 3:
                value = PCF8591.AIN3.read(device);  break;
            default:
                break;
        }
        return value;
    }
    
    public void analogWrite(int pwm) {
        PCF8591.AOUT.write(device, pwm);
    }
}
