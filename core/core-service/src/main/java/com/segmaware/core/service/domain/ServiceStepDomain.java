package com.segmaware.core.service.domain;

import com.segmaware.common.service.domain.BaseDomain;
import com.segmaware.core.dataaccess.entity.ServiceCatalog;
import com.segmaware.core.dataaccess.entity.ServiceStep;
import com.segmaware.core.dataaccess.repo.ServiceStepRepository;
import com.segmaware.utility.api.exception.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class ServiceStepDomain extends BaseDomain<UUID, ServiceStep> {
    private final ServiceStepRepository repository;
    public ServiceStepDomain(ServiceStepRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public UUID addServiceStep(ServiceCatalog serviceCatalog, ServiceStep serviceStep){
        serviceStep.setServiceCatalog(serviceCatalog);
        return save(serviceStep).getId();
    }

    public ServiceStep getEntityByKey(String key){
        return repository.findByStepKey(key)
                .orElseThrow(() -> new RecordNotFoundException());
    }

    public List<ServiceStep> getServiceStepsByService(ServiceCatalog serviceCatalog) {
        return repository.findByServiceCatalog(serviceCatalog);
    }

    public List<ServiceStep> getRemainingSteps(List<UUID> stepsIds, ServiceCatalog serviceCatalog) {
        return repository.findByIdNotInAndServiceCatalog(stepsIds, serviceCatalog);
    }
}
