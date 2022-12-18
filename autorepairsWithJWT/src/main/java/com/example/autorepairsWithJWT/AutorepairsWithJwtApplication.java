package com.example.autorepairsWithJWT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AutorepairsWithJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutorepairsWithJwtApplication.class, args);
    }

    @Bean
    public RestTemplate create(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}
