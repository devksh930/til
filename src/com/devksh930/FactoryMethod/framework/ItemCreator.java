package com.devksh930.FactoryMethod.framework;

public abstract class ItemCreator {


    public Item create(){ //템플릿 메소드와 같은형태
        Item item;
        //step1
        requestItemsInfo();
        //step2
        item = createItem();
        //step3
        createItemLog();

        return item;
    }

    //아이템 생성전 db에 아이템 정보요청
    abstract protected void requestItemsInfo();
    //아이템 생성정보를 db에 넘긴다
    abstract protected void createItemLog();
    //아이템 생성 알고리즘
    abstract protected Item createItem();
}
