package com.devksh930.Adapter;

public class AdapterMain {
    public static void main(String[] args) {
        Adapter adapter = new AdapterImpl();

        System.out.println(adapter.twiceOf(100f));

        System.out.println(adapter.halfOf(100f));

    }
}
