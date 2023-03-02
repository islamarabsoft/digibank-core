package com.segmaware.core.service.domain;

import com.segmaware.common.service.domain.BaseDomain;
import com.segmaware.core.dataaccess.entity.ServiceCatalog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ServiceCatalogDomain extends BaseDomain<UUID, ServiceCatalog> {
    public ServiceCatalogDomain(JpaRepository<ServiceCatalog, UUID> jpaRepository) {
        super(jpaRepository);
    }
}
