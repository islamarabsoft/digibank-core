package com.segmaware.core.dataaccess.repo;

import com.segmaware.core.dataaccess.entity.ServiceCatalog;
import com.segmaware.core.dataaccess.entity.ServiceStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceStepRepository extends JpaRepository<ServiceStep, UUID> {
    List<ServiceStep> findByIdNotInAndServiceCatalog(Collection<UUID> ids, ServiceCatalog serviceCatalog);
    List<ServiceStep> findByServiceCatalog(ServiceCatalog serviceCatalog);
    Optional<ServiceStep> findByStepKey(String stepKey);
}