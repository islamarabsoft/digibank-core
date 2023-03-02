package com.segmaware.core.service;

import com.segmaware.core.service.dto.UserInfoDto;

import java.util.UUID;

public interface AccessInformationLoggingService {
    UserInfoDto logAccess(UserInfoDto userInfoDto);
}
