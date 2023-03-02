package com.segmaware.core.service.impl;

import com.segmaware.core.service.ServiceCatalogService;
import com.segmaware.core.service.domain.ActivityDomain;
import com.segmaware.core.service.domain.ServiceCatalogDomain;
import com.segmaware.core.service.domain.ServiceStepDomain;
import com.segmaware.core.service.dto.ServiceStepDto;
import com.segmaware.core.service.mapper.ServiceStepMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServiceCatalogServiceImpl implements ServiceCatalogService {

    private final ServiceStepDomain serviceStepDomain;
    private final ServiceCatalogDomain serviceCatalogDomain;
    private final ServiceStepMapper serviceStepMapper;

    @Override
    public UUID createServiceStep(UUID serviceId, ServiceStepDto serviceStepDto) {
        return serviceStepDomain.addServiceStep(
                serviceCatalogDomain.getEntity(serviceId), serviceStepMapper.toEntity(serviceStepDto));
    }

    @Override
    public Map<UUID, String> getServiceSteps(UUID serviceId) {
        Map<UUID, String> serviceSteps = new HashMap<>();
        serviceStepDomain.getServiceStepsByService(serviceCatalogDomain
                .getEntity(serviceId)).forEach(serviceStep ->
                serviceSteps.put(serviceStep.getId(), serviceStep.getStepKey()));
        return serviceSteps;
    }
}
