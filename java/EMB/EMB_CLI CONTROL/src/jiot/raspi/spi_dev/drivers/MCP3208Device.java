/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.spi_dev.drivers;

import java.io.IOException;
import java.nio.ByteBuffer;
import jdk.dio.Device;
import jiot.raspi.spi_dev.MCP3208;
import jiot.raspi.spi_dev.SPIRPI;

/**
 *
 * @author yjkim
 */
public class MCP3208Device extends SPIRPI {
    
    /**
     *
     * @param address
     * @throws IOException
     */
    public MCP3208Device(int address) throws IOException {
        super(address, Device.BIG_ENDIAN);  // (SPI address, bit-ordering)
    }

    /**
     *
     * @param channel
     * @return
     * @throws IOException
     */
    public int analogRead(int channel) throws IOException {
        ByteBuffer sndBuf = ByteBuffer.allocate(3);
        ByteBuffer rcvBuf = ByteBuffer.allocate(3);
        
        channel = (channel << 14) | MCP3208.READ_CHANNEL.cmd;
        sndBuf.put((byte)((channel >> 16) & 0xff));
        sndBuf.put((byte)((channel >> 8) & 0xff));
        sndBuf.put((byte)(channel & 0xff));
        sndBuf.flip();
        
        writeAndRead(sndBuf, rcvBuf);

        int highByte = (int)(rcvBuf.get(1) & 0x0f);
        int lowByte = (int)(rcvBuf.get(2) & 0xff);

        return (highByte << 8) | lowByte;   // return 12-bit digital value
    }

    /**
     *
     * @param data
     * @return
     */
    public float convertVolts(int data) {
        return (float) (data * 5.0) / (float) 4095;
    }
    
}
