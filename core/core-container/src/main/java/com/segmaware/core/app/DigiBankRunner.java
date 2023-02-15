package com.segmaware.core.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.segmaware.core.controller", "com.segmaware.core.app.config"})
@EnableFeignClients
public class DigiBankRunner {
    public static void main(String ... args){
        SpringApplication.run(DigiBankRunner.class);
    }
}
