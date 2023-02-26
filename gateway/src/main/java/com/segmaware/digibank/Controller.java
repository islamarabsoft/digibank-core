package com.segmaware.digibank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Controller {

    @GetMapping("/api/1")
    public String api() {
        // your code here
        return "return api " ;
    }
}
