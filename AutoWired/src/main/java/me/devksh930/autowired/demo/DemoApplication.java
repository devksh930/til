package me.devksh930.autowired.demo;

import me.devksh930.out.Myservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Supplier;

@SpringBootApplication
public class DemoApplication {

//    @Autowired
//    Myservice myservice; //다른 패키지라서 component scan이 불가하다. me.devksh930.autowired.demo에서 component scan의 범위를 확인하라

    public static void main(String[] args) {
        //함수를 사용한 빈등록
        var app = new SpringApplication(DemoApplication.class);
        app.addInitializers((ApplicationContextInitializer<GenericApplicationContext>) ctx ->
        {
            ctx.registerBean(Myservice.class);
            ctx.registerBean(ApplicationRunner.class, () -> args1 -> System.out.println("Functional Bean Definition"));

        });
        app.run(args);
    }

}