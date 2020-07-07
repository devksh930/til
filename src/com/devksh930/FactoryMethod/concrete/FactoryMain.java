package com.devksh930.FactoryMethod.concrete;

import com.devksh930.FactoryMethod.framework.Item;
import com.devksh930.FactoryMethod.framework.ItemCreator;

public class FactoryMain {
    public static void main(String[] args) {
        ItemCreator creator;
        Item item;

        creator = new HpCreator();
        item = creator.create();
        item.use();


        creator = new MpCreator();
        creator.create();
        item.use();


    }
}
