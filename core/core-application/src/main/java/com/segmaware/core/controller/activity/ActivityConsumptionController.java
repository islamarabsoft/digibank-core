package com.segmaware.core.controller.activity;

import com.segmaware.core.service.ActivityService;
import com.segmaware.core.service.dto.ActivityFeedsDto;
import com.segmaware.utility.api.response.ApiLogging;
import com.segmaware.utility.api.response.RestResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/consume")
@RolesAllowed({"integration"})
@RequiredArgsConstructor
public class ActivityConsumptionController {
    private final ActivityService activityService;

    @ApiLogging
    @RestResponseEntity
    @GetMapping("/activity")
    public ActivityFeedsDto consumeActivity(Authentication authentication){
        return activityService.consumeActivityFeedsByActivity(authentication.getName());
    }

    @ApiLogging
    @RestResponseEntity
    @GetMapping("/activity/{serviceStepId}")
    public ActivityFeedsDto consumeServiceStepActivity
            (@PathVariable UUID serviceStepId, Authentication authentication){
        return activityService
                .consumeActivityFeedsByActivityAndServiceStep(authentication.getName(), serviceStepId);
    }

    @ApiLogging
    @RestResponseEntity
    @GetMapping("/activity/key/{serviceStepKey}")
    public ActivityFeedsDto consumeServiceStepActivity
            (@PathVariable String serviceStepKey, Authentication authentication){
        return activityService
                .consumeActivityFeedsByActivityAndServiceStep(authentication.getName(), serviceStepKey);
    }
}
