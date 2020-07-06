package me.devksh930.springapplicationcontext;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
@SpringBootApplication //이에노테이션만 붙어있으면 applicationcotext를 스프링이 알아서 해준
public class DemoApplication {

    public static void main(String[] args) {
//        java config 클래스를 이용한 방법
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

//        application.xml을 이용한 빈설정
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        BookService bookService = (BookService) context.getBean("bookService");
        System.out.println(bookService.bookRepository!=null);
    }

}
