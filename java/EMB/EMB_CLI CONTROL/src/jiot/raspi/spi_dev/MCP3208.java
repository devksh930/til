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
public enum MCP3208 {

    /**
     * Read from a channel a digital value equal to analog channel value
     */
    READ_CHANNEL(0x060000);         // 24-bit command
        
    /**
     *
     */
    public int cmd;

    private MCP3208(int cmd) {
        this.cmd = cmd;
    }    
}
