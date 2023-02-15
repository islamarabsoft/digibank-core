package com.segmaware.core.controller;

import com.segmaware.utility.api.response.RestResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/activity")
public class Activity {
    @RestResponseEntity
    @GetMapping
    public String createActivity(){
        log.info("Execute Function");
        return "Activity Message Display";
    }
}
