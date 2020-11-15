package com.devksh930.FactoryMethod.concrete;

import com.devksh930.FactoryMethod.framework.Item;

public class MpPotion implements Item {
    @Override
    public void use() {
        System.out.println("마력 회복");
    }
}
