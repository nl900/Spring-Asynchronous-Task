package com.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsynchronousSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsynchronousSpringApplication.class, args);
    }

}
