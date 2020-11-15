package me.devksh930.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class ApplicationArgs {
    public ApplicationArgs(ApplicationArguments arguments) {
        
        System.out.println("foo: "+ arguments.containsOption("foo"));
        System.out.println("bar: "+arguments.containsOption("bar"));
    }
}
