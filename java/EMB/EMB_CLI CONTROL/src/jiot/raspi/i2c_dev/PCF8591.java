/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.i2c_dev;

import jdk.dio.i2cbus.I2CDevice;

/**
 *
 * @author yjkim
 */
public enum PCF8591 {
    // Analog input 0
    AIN0((byte)0x00),
    // Analog input 1
    AIN1((byte)0x01),
    // Analog input 2
    AIN2((byte)0x02),
    // Analog input 3
    AIN3((byte)0x03),
    // Analog Output
    AOUT((byte)0x40);
    
    private byte cmd;
    
    private PCF8591(byte cmd) {
        this.cmd = cmd;
    }
    
    public int read(I2CDevice device) {
        return I2CUtils.read(device, this.cmd);
    }
    
    public void write(I2CDevice device, int value) {
        I2CUtils.write(device, this.cmd, (byte)value);
    }
}
