package com.kuimov.pp.task351.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GetRestTemplate {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
