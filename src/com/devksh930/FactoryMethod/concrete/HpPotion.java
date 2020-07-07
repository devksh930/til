package com.devksh930.FactoryMethod.concrete;

import com.devksh930.FactoryMethod.framework.Item;

public class HpPotion implements Item {
    @Override
    public void use() {
        System.out.println("최력 회복!");
    }
}
