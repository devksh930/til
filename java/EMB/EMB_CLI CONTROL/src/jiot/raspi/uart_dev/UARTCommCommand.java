/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.uart_dev;

import java.io.IOException;

/**
 *
 * @author yjkim
 */
public enum UARTCommCommand {
    GET_TMP("TMP\n"),
    GET_HMD("HMD\n"),
    GET_ACK("ACK\n");
    
    public String cmd;
    
    private UARTCommCommand(String cmd) {
        this.cmd = cmd;
    }

    public void send(UARTRPI uart) throws IOException, InterruptedException {
        if (!uart.sendSync(this.cmd)) {
            throw new IOException("Semaphore Acquire Timeout");
        }
    }
}
