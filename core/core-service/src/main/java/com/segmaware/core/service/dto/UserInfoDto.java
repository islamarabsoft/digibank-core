package com.segmaware.core.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Builder
public class UserInfoDto {
    private String userId;
    private String userName;
    private String ip;
    private long authTime;
    private String sessionId;
    private String issuer;
}
