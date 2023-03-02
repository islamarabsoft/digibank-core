package com.segmaware.core.controller.activity;

import com.segmaware.core.service.ServiceCatalogService;
import com.segmaware.core.service.dto.ServiceStepDto;
import com.segmaware.utility.api.response.ApiLogging;
import com.segmaware.utility.api.response.RestResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/service")
@RolesAllowed({"admin"})
@RequiredArgsConstructor
public class AdminServiceController {

    private final ServiceCatalogService serviceCatalogService;
    @ApiLogging
    @RestResponseEntity
    @PostMapping("/step/{serviceId}")
    public UUID activitySteps(@PathVariable UUID serviceId,
                              @RequestBody ServiceStepDto serviceStepDto){
        return serviceCatalogService.createServiceStep(serviceId, serviceStepDto);
    }

    @ApiLogging
    @RestResponseEntity
    @PostMapping("/step/setting/{serviceId}")
    public UUID setting(@PathVariable UUID serviceId, Authentication authentication){
        return null;
    }
}
