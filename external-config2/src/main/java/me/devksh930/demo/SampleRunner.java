package me.devksh930.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Autowired
    SunghoProperties sunghoProperties;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("================");
        System.out.println(sunghoProperties.getName());
        System.out.println(sunghoProperties.getAge());
        System.out.println(sunghoProperties.getSessionTimeout());
        System.out.println("================");
    }
}
