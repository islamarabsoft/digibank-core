package com.segmaware.core.controller;

import com.segmaware.utility.api.response.ApiLogging;
import com.segmaware.utility.api.response.RestResponseEntity;
import com.segmaware.utility.helper.basic.StringMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@Slf4j
@RestController
@RequestMapping("/user/info")
@RolesAllowed({"user"})
public class UserInfo {
    @RestResponseEntity
    @GetMapping
    @ApiLogging
    public StringMessage createActivity(Authentication authentication){
        WebAuthenticationDetails details
                = (WebAuthenticationDetails) authentication.getDetails();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return StringMessage.builder()._message("User Information Display " +
                " Name " + authentication.getName() +
                " User IP " + details.getRemoteAddress() +
                " Authorities " + authentication.getAuthorities() +
                " Jwt Info " + jwt.getId() + " Claims " + jwt.getClaims())
                .build();
    }
}
