/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.spi_dev;

/**
 *
 * @author yjkim
 */
public class SPIUtils {
    /**
     *
     * @param mili
     */
    public static void SPIdelay(int mili) {
        try {
            Thread.sleep(mili);
        } catch (InterruptedException ex) {
        }
    }

    /**
     *
     * @param mili
     * @param nano
     */
    public static void SPIdelayNano(int mili, int nano) {
        try {
            Thread.sleep(mili, nano);
        } catch (InterruptedException ex) {
        }
    }

    /**
     *
     * @param b
     * @return byte values from -127..128 convert 128..255
     */
    public static int asInt(byte b) {
        int i = b;
        if (i < 0) {
            i += 256;
        }
        return i;
    }    
}
