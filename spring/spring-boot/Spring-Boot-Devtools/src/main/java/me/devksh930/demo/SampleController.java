package me.devksh930.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    SampleService sampleService;

    @GetMapping("/hello")
    public String hello() {
        return "hello " +sampleService.getName();
    }
}
