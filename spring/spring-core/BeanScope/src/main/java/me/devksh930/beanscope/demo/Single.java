package me.devksh930.beanscope.demo;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Single {
    @Autowired
//    private ObjectProvider<Proto> proto; //Object-Provider 를 이용하여 참조
    private Proto proto;

    public Proto getProto() {
//        return proto.getIfAvailable(); //Object-Provider 를 이용하여 참조
        return proto;
    }
}
