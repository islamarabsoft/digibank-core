package com.segmaware.core.controller.activity;

import com.segmaware.core.service.ActivityService;
import com.segmaware.core.service.ServiceCatalogService;
import com.segmaware.core.service.dto.ActivityDto;
import com.segmaware.utility.api.response.ApiLogging;
import com.segmaware.utility.api.response.RestResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/digital/activity")
@RolesAllowed({"user"})
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;
    private final ServiceCatalogService serviceCatalogService;

    @RestResponseEntity
    @ApiLogging
    @PostMapping
    public UUID addActivity(@Valid @RequestBody ActivityDto activityDto, Authentication authentication){
        return activityService.addActivity(activityDto, authentication.getName());
    }

    @RestResponseEntity
    @ApiLogging
    @PostMapping("/feeds/{serviceStepId}/{activityId}")
    public List<UUID> addActivityFeeds(@PathVariable UUID serviceStepId, @PathVariable UUID activityId,
                                       @Valid @RequestBody Map<String, Object> activityDetailParam,
                                       Authentication authentication){
        return activityService.addActivityFeeds(serviceStepId, activityId, activityDetailParam, authentication.getName());
    }

    @RestResponseEntity
    @ApiLogging
    @GetMapping("/service/steps/{serviceId}")
    public Map<UUID, String> getServiceSteps(@PathVariable UUID serviceId){
        return serviceCatalogService.getServiceSteps(serviceId);
    }
}
