package com.segmaware.core.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableJpaRepositories(basePackages = {"com.segmaware.core.dataaccess.repo"})
@EntityScan(basePackages = {"com.segmaware.core.dataaccess.entity",
        "com.segmaware.common.dataaccess.entity"})
@SpringBootApplication(scanBasePackages = {"com.segmaware.core.controller",
        "com.segmaware.core.app.config", "com.segmaware.utility", "com.segmaware.core.service"})
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class DigiBankRunner {
    public static void main(String ... args){
        SpringApplication.run(DigiBankRunner.class);
    }
}
