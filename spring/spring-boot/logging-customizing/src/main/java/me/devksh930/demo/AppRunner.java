package me.devksh930.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Autowired
    private String hello;

    @Autowired
    private SunghoProperties properties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("========================");
        logger.debug(hello);
        logger.debug(properties.getName());
        logger.debug(properties.getFullname());
        logger.debug("========================");
    }
}
