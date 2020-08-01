package me.devksh930.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DemoApplication.class)
                .run(args);
    }
//        SpringApplication app = new SpringApplication(DemoApplication.class);
////        app.setBannerMode(Banner.Mode.OFF);
//        app.setBanner(new Banner() {
//            @Override
//            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//                out.println("======================");
//                out.println("DevKsh930");
//                out.println("======================");
//            }
//        });
//        app.run(args);
//    }

}
