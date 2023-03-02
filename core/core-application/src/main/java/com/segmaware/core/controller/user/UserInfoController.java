package com.segmaware.core.controller.user;

import com.segmaware.core.service.AccessInformationLoggingService;
import com.segmaware.core.service.dto.UserInfoDto;
import com.segmaware.utility.api.response.ApiLogging;
import com.segmaware.utility.api.response.RestResponseEntity;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserInfoController {

    private final AccessInformationLoggingService accessInformationLoggingService;

    @RestResponseEntity
    @GetMapping
    @ApiLogging
    public UserInfoDto userInfo(Authentication authentication){
        WebAuthenticationDetails details
                = (WebAuthenticationDetails) authentication.getDetails();
        Jwt jwt = (Jwt) authentication.getPrincipal();

        return accessInformationLoggingService.logAccess(
                    UserInfoDto.builder()
                            .userId(jwt.getClaims().get("sid").toString())
                            .userName(authentication.getName())
                            .authTime(Long.parseLong(jwt.getClaims().get("auth_time").toString()))
                            .ip(details.getRemoteAddress())
                            .sessionId(jwt.getId())
                            .issuer(jwt.getIssuer().toString())
                            .build());
    }

}
