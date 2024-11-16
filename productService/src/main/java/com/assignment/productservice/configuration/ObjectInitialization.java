package com.assignment.productservice.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ObjectInitialization {

    @Bean
    public RestTemplate initializeRestTemplate() {
        return new RestTemplateBuilder().build();

    }
}
