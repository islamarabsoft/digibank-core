package com.segmaware.core.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication(scanBasePackages = {"com.segmaware.core.controller",
        "com.segmaware.core.app.config", "com.segmaware.utility"})
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class DigiBankRunner {
    public static void main(String ... args){
        SpringApplication.run(DigiBankRunner.class);
    }
}
