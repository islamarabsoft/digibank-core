package com.segmaware.core.service.domain;

import com.segmaware.common.service.domain.BaseDomain;
import com.segmaware.core.dataaccess.entity.AccessLogging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class AccessLoggingDomain extends BaseDomain<UUID, AccessLogging> {

    public AccessLoggingDomain(JpaRepository<AccessLogging, UUID> jpaRepository) {
        super(jpaRepository);
    }

}
