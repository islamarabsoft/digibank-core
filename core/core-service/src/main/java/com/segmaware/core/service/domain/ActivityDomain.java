package com.segmaware.core.service.domain;

import com.segmaware.common.service.domain.BaseDomain;
import com.segmaware.core.dataaccess.entity.Activity;
import com.segmaware.core.dataaccess.entity.ActivityStatus;
import com.segmaware.core.dataaccess.entity.ServiceCatalog;
import com.segmaware.core.dataaccess.entity.ServiceStep;
import com.segmaware.core.dataaccess.repo.ActivityRepository;
import com.segmaware.core.service.ConistantVariables;
import com.segmaware.utility.api.exception.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class ActivityDomain extends BaseDomain<UUID, Activity> {

    private final ActivityRepository activityRepository;
    private final ActivityServiceStepActivityFeedsDomain activityServiceStepActivityFeedsDomain;
    private final ServiceStepDomain serviceStepDomain;
    public ActivityDomain(ActivityRepository activityRepository, ActivityServiceStepActivityFeedsDomain activityServiceStepActivityFeedsDomain, ServiceStepDomain serviceStepDomain) {
        super(activityRepository);
        this.activityRepository = activityRepository;
        this.activityServiceStepActivityFeedsDomain = activityServiceStepActivityFeedsDomain;
        this.serviceStepDomain = serviceStepDomain;
    }

    public Activity addActivity(ServiceCatalog serviceCatalog, String username){
        return save(Activity.builder()
                    .status(ActivityStatus.READY)
                    .serviceCatalog(serviceCatalog)
                    .description(serviceCatalog.getName() + " in status " + ActivityStatus.READY)
                    .name("Activity " + serviceCatalog.getName())
                    .username(username)
                    .build());
    }

    public boolean checkActivityExistWithSameUser(String username)  {
        if(activityRepository.
                countByUsernameIgnoreCaseAndStatusIn(username, ConistantVariables.ACTIVE_ACTIVITIES_STATUSES) > 0)
            return true;
        return false;
    }

    public Activity activeActivityByUsername(String username){
        return activityRepository.
                findByUsernameIgnoreCaseAndStatusIn
                        (username, ConistantVariables.ACTIVE_ACTIVITIES_STATUSES)
                .orElseThrow(() -> new RecordNotFoundException());
    }

    public Activity changeStatus(Activity activity) {
        //Check all steps assigned to  activity are consumed to close else make it's processing
        if(serviceStepDomain.getRemainingSteps(
                activityServiceStepActivityFeedsDomain
                        .getServiceStepsForActivity(activity), activity.getServiceCatalog()).size() > 0)
            activity.setStatus(ActivityStatus.PROCESSING);
        else activity.setStatus(ActivityStatus.CONSUMED);
        return activity;
    }
}
