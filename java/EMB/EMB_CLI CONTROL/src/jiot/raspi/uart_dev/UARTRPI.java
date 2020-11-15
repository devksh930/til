/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.uart_dev;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.dio.DeviceManager;
import jdk.dio.uart.UART;
import jdk.dio.uart.UARTConfig;
import jdk.dio.uart.UARTEvent;
import jdk.dio.uart.UARTEventListener;

/**
 *
 * @author yjkim
 */
public class UARTRPI {
    private static AtomicReference<UARTRPI> instance =
            new AtomicReference<UARTRPI>();
    
    public static UARTRPI getInstance() throws IOException {
        if (instance.get() == null) {
            instance.set(new UARTRPI(null));
        }
        return instance.get();
    }

    public static UARTRPI getInstance(String controllerName) throws IOException {
        if (instance.get() == null) {
            instance.set(new UARTRPI(controllerName));
        }
        return instance.get();
    }
    
    public interface UARTDataListener {
        boolean processReceivedData(String data);
    }
    
    private final Map<String, UARTDataListener> dataProcList =
            new HashMap<String, UARTDataListener>();
    
    public void addDataListener(String devName, UARTDataListener listener) {
        dataProcList.put(devName, listener);
    }
    
    public void removeDataListener(String devName) {
        dataProcList.remove(devName);
    }
    
    private UART port = null;
    private final Semaphore dataReceived = new Semaphore(0, true);
    
    private UARTRPI(String controllerName) throws IOException {
        if (controllerName == null)
            controllerName = "ttyAMA0";
        
        UARTConfig config = new UARTConfig.Builder()
                .setControllerName(controllerName)
                .setChannelNumber(1)
                .setBaudRate(115200)
                .setDataBits(UARTConfig.DATABITS_8)
                .setStopBits(UARTConfig.STOPBITS_1)
                .setParity(UARTConfig.PARITY_NONE)
                .setFlowControlMode(UARTConfig.FLOWCONTROL_NONE)
                .build();
        
        port = (UART)DeviceManager.open(config);
        
        port.setEventListener(UARTEvent.INPUT_DATA_AVAILABLE, new UARTEventListener() {
            @Override
            public void eventDispatched(UARTEvent uarte) {
                if (uarte.getID() == UARTEvent.INPUT_DATA_AVAILABLE) {
                    ByteBuffer buffer = ByteBuffer.allocateDirect(100);
                    
                    try {
                        int length = port.read(buffer);
                        byte[] bytes = new byte[length];
                        buffer.flip();
                        buffer.get(bytes);
                        String response = new String(bytes);
                        
                        boolean release = false;
                        Iterator<String> keys = dataProcList.keySet().iterator();
                        while (keys.hasNext()) {
                            release = dataProcList.get(keys.next()).processReceivedData(response);
                            if (release)  break;
                        }
                        
                        if (release) {
                            dataReceived.release();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(UARTRPI.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                }
                else {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            }
        });
    }
    
    public void close() throws IOException {
        if (port != null && dataProcList.size() == 0) {
            port.close();
            instance.set(null);
            port = null;
        }
    }
    
    public UART getPort() {
        return port;
    }
    
    public void send(ByteBuffer data) throws IOException {
        port.write(data);
    }

    public void send(byte[] data, int length) throws IOException {
        ByteBuffer out = ByteBuffer.allocateDirect(length);
        out.put(data);
        out.clear();
        port.write(out);
    }

    public void send(String data) throws IOException {
        ByteBuffer out = ByteBuffer.allocateDirect(data.length());
        out.put(data.getBytes());
        out.clear();
        port.write(out);
    }
    
    public boolean sendSync(String data) throws IOException, InterruptedException {
        send(data);
//        dataReceived.acquire();
//        if (!dataReceived.tryAcquire(60000, TimeUnit.MILLISECONDS)) {
//            throw new IOException("Semaphore Acquire Timeout");
//        }
        return dataReceived.tryAcquire(60000, TimeUnit.MILLISECONDS);
    }
    
    public int readData(ByteBuffer buf) throws IOException {
        return port.read(buf);
    }
}
