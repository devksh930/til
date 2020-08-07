package me.devksh930.demo;

import me.devksh930.demo.account.Account;
import me.devksh930.demo.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {
    @Autowired
    AccountService accountService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account test = accountService.createAccount("test", "1234");
        System.out.println(test.getUsername() + " password: " + test.getPassword());
    }
}
