package me.devksh930.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RestRunner implements ApplicationRunner {
    @Autowired
//    RestTemplateBuilder restTemplateBuilder;
    WebClient.Builder build;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//
//        //TODO /hello
//        String helloresult = restTemplate.getForObject("http://localhost:8080/hello", String.class);
//        System.out.println(helloresult);
//
//        //TODO /world
//        String worldresult = restTemplate.getForObject("http://localhost:8080/world", String.class);
//        System.out.println(worldresult);
//
//        stopWatch.stop();
//        System.out.println(stopWatch.prettyPrint());

        WebClient webClient = build.build();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //TODO /hello
        Mono<String> hellomono = webClient.get().uri("http://localhost:8080/hello")
                .retrieve()
                .bodyToMono(String.class);
        hellomono.subscribe(s -> {
            System.out.println(s);

            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            System.out.println(stopWatch.prettyPrint());
            stopWatch.start();
        });


        //TODO /world
        Mono<String> worldmono = webClient.get().uri("http://localhost:8080/world")
                .retrieve()
                .bodyToMono(String.class);
        worldmono.subscribe(s -> {

            System.out.println(s);

            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            System.out.println(stopWatch.prettyPrint());
            stopWatch.start();
        });

    }
}
