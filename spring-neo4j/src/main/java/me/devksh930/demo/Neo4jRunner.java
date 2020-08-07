package me.devksh930.demo;

import me.devksh930.demo.account.Account;
import me.devksh930.demo.account.AccountRepository;
import me.devksh930.demo.account.Role;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner {

    @Autowired
    AccountRepository accountRepository;
//    @Autowired
//    SessionFactory sessionFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setEmail("test@icloud.com");
        account.setUsername("test");

        Role role = new Role();
        role.setName("user");
        account.getRoles().add(role);

        accountRepository.save(account);

//        Session session = sessionFactory.openSession();
//        session.save(account);
//        sessionFactory.close();

    }
}
