package me.devksh930.springapplicationcontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//JAVA파일을 이용한 bean 설정
@Configuration
@ComponentScan(basePackageClasses = DemoApplication.class)
public class ApplicationConfig {
//    @Bean
//    public BookRepository bookRepository() {
//        return new BookRepository();
//    }
//
//    @Bean
//    public BookService bookService() {
//        return new BookService();
//    }
}
