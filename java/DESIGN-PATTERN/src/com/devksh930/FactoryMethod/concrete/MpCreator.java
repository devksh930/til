package com.devksh930.FactoryMethod.concrete;

import com.devksh930.FactoryMethod.framework.Item;
import com.devksh930.FactoryMethod.framework.ItemCreator;

import java.util.Date;

public class MpCreator extends ItemCreator {

    @Override
    protected void requestItemsInfo() {
        System.out.println("데이터베이스에서 마력 회복 물약 정보를 가져온다");
    }

    @Override
    protected void createItemLog() {
        System.out.println("마력 회복물약을 새로 생성 했습니다"+new Date());

    }

    @Override
    protected Item createItem() {
        //작업
        return new MpPotion();
    }
}
