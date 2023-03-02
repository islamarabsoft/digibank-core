package com.segmaware.core.service;

import com.segmaware.core.service.dto.ServiceStepDto;

import java.util.Map;
import java.util.UUID;

public interface ServiceCatalogService {
    UUID createServiceStep(UUID serviceCatalogId, ServiceStepDto serviceStepDto);

    Map<UUID, String> getServiceSteps(UUID serviceId);
}
