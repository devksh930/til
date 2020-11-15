/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.things;

import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author yjkim
 */
public abstract class ControlPoint extends Observable {
    public static enum Type {
        DI, DO, AI, AO, BI, BO, SI, SO
    };
    
    private static final AtomicInteger COUNT = new AtomicInteger(0);

    protected static final ScheduledExecutorService POLLING =
            Executors.newSingleThreadScheduledExecutor();

    public static void shutdownExecutor() {
        POLLING.shutdown();
    }
    
    private int id;
    private String name;
    protected AtomicInteger presentValue = new AtomicInteger(0);
    
    public ControlPoint() {
        id = COUNT.getAndIncrement();
        name = getClass().getName() + "-" + id;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
        fireChanged("name");
    }
    
    public int getPresentValue() {
        return presentValue.get();
    }
    
    protected void fireChanged() {
        setChanged();
        notifyObservers();
    }

    protected void fireChanged(Object arg) {
        setChanged();
        notifyObservers(arg);
    }
    
    public abstract void open();
    
    public abstract void close();
    
    public abstract boolean isEnabled();
    
    public abstract Type getType();

    @Override
    public String toString() {
        return getName() + "(" + getId() + ") [type=" + getType() + ", enabled=" + isEnabled() + "]";
    }
}
