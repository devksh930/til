package me.devksh930.demo;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private String hello;

    @Autowired
    private SunghoProperties sunghoProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("==================");
        System.out.println(hello);
        System.out.println(sunghoProperties.getName());
        System.out.println(sunghoProperties.getFullname());
        System.out.println("==================");

    }
}
