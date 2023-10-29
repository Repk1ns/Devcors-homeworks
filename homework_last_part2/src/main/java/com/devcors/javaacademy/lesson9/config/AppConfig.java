package com.devcors.javaacademy.lesson9.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Queue queue() {
        return new Queue("carLocationQueue");
    }
}
