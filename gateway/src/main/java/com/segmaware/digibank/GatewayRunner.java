package com.segmaware.digibank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class GatewayRunner {
    public static void main(String[] args) {
        SpringApplication.run(GatewayRunner.class, args);
    }
}