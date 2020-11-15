package me.devksh930.demo;

import me.devksh930.demo.Account.Account;
import me.devksh930.demo.Account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class DemoApplication {


    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            Account account = new Account();
            account.setEmail("aaa@bbb");
            account.setUsername("sungho");

            accountRepository.insert(account);
            System.out.println("finished");

        };
    }
}
