package com.segmaware.core.service.impl;

import com.segmaware.core.service.AccessInformationLoggingService;
import com.segmaware.core.service.domain.AccessLoggingDomain;
import com.segmaware.core.service.dto.UserInfoDto;
import com.segmaware.core.service.mapper.UserAccessMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AccessInformationLoggingServiceImpl
        implements AccessInformationLoggingService {
    private final AccessLoggingDomain accessLoggingDomain;
    private final UserAccessMapper userAccessMapper;

    @Override
    public UserInfoDto logAccess(UserInfoDto userInfoDto) {
        return userAccessMapper.toDto(
                accessLoggingDomain.save(
                        userAccessMapper.toEntity(userInfoDto)));
    }
}
