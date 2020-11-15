/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.i2c_dev;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.dio.DeviceManager;
import jdk.dio.i2cbus.I2CDevice;
import jdk.dio.i2cbus.I2CDeviceConfig;

/**
 *
 * @author yjkim
 */
public class I2CRPI {
    private I2CDeviceConfig config;
    public I2CDevice device = null;

    public I2CRPI(int i2cAddress) throws IOException {
        config = new I2CDeviceConfig.Builder().setAddress(i2cAddress, I2CDeviceConfig.ADDR_SIZE_7).build();
        device = (I2CDevice)DeviceManager.open(I2CDevice.class, config);
    }
    
    public void close() {
        try {
            if (device != null)
                device.close();
        } catch (IOException ex) {
            Logger.getLogger(I2CRPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
