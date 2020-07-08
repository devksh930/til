package me.devksh930.applicationeventpublisher.demo;


//POJO spring 소스코드가 들어가지 않는다
public class MyEvent {

    private int data;

    private Object source;

    public MyEvent(Object source, int data) {
        this.source = source;
        this.data = data;

    }

    public int getData() {
        return data;
    }

    public Object getSource() {
        return source;
    }
}
