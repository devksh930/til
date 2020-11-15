/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.i2c_dev;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.dio.i2cbus.I2CDevice;

/**
 *
 * @author yjkim
 */
public class I2CUtils {
    public static void I2Cdelay(int milli) {
        try {
            Thread.sleep(milli);
        } catch (InterruptedException ex) {
            Logger.getLogger(I2CUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void I2CdelayNano(int milli, int nano) {
        try {
            Thread.sleep(milli, nano);
        } catch (InterruptedException ex) {
            Logger.getLogger(I2CUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int asInt(byte b) {
        int i = b;
        if (i < 0) {
            i += 256;
        }
        return i;
    }
    
    public static int read(I2CDevice device, int cmd) {
        ByteBuffer rxBuf = ByteBuffer.allocate(1);
        try {
            device.read(cmd, 1, rxBuf);
        } catch (IOException ex) {
            Logger.getLogger(I2CUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("WARNING: " + ex.getMessage());
        }
        return asInt(rxBuf.get(0));
    }
    
    public static int readShort(I2CDevice device, int cmd) {
        ByteBuffer rxBuf = ByteBuffer.allocate(2);
        try {
            device.read(cmd, 1, rxBuf);
        } catch (IOException ex) {
            Logger.getLogger(I2CUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("WARNING: " + ex.getMessage());
        }
        return rxBuf.getShort();
    }
    
    public static void write(I2CDevice device, byte cmd, byte value) {
        ByteBuffer txBuf = ByteBuffer.allocate(2);
        txBuf.put(0, cmd);
        txBuf.put(1, value);
        try {
            device.write(txBuf);
        } catch (IOException ex) {
            Logger.getLogger(I2CUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("WARNING: " + ex.getMessage());
        }
    }
    
}
