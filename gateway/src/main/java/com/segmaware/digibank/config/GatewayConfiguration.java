package com.segmaware.digibank.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class GatewayConfiguration {
    private String httpbin = "http://httpbin.org:80";

    public String getHttpbin() {
        return httpbin;
    }

    public void setHttpbin(String httpbin) {
        this.httpbin = httpbin;
    }
}
