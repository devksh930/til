package me.devksh930.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@TestPropertySource(properties = "devksh930.name=ksh930")
@TestPropertySource(locations = "classpath:/test.properties")
@SpringBootTest
//@SpringBootTest(properties = "devksh930.name=ksh930")
public class DemoApplicationTests {

    @Autowired
    Environment environment;

    @Test
    public void contextLoads() {

        assertThat(environment.getProperty("devksh930.name"))
                .isEqualTo("sungho");
    }

}
