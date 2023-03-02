package com.segmaware.core.service.domain;

import com.segmaware.common.service.domain.BaseDomain;
import com.segmaware.core.dataaccess.entity.*;
import com.segmaware.core.dataaccess.repo.ActivityServiceStepActivityFeedsRepository;
import com.segmaware.core.service.ConistantVariables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class ActivityServiceStepActivityFeedsDomain
        extends BaseDomain<UUID, ActivityServiceStepActivityFeeds> {
    ActivityServiceStepActivityFeedsRepository repository;
    public ActivityServiceStepActivityFeedsDomain(
            ActivityServiceStepActivityFeedsRepository jpaRepository) {
        super(jpaRepository);
        this.repository = jpaRepository;
    }

    public int countOfActiveActivityFeedsForUser
            (Activity activity, ServiceStep serviceStep, String username) {
        return repository.
                countByUsernameAndActivityAndServiceStepAndActivityFeedsStatus
                        (username, activity, serviceStep, ActivityFeedsStatus.ACTIVE);
    }

    public List<ActivityServiceStepActivityFeeds>
        getActivityFeedsByServiceStepForUser(ServiceStep serviceStep, String username){
        return repository.findByUsernameAndActivityFeedsStatusAndActivity_StatusInAndServiceStep(
            username, ActivityFeedsStatus.ACTIVE, ConistantVariables.ACTIVE_ACTIVITIES_STATUSES, serviceStep);
    }

    public List<ActivityServiceStepActivityFeeds>
        getActiveActivityFeedsForUser(String username){
            return repository.findByUsernameAndActivityFeedsStatusAndActivity_StatusIn(
                    username, ActivityFeedsStatus.ACTIVE, ConistantVariables.ACTIVE_ACTIVITIES_STATUSES);
    }

    public ActivityServiceStepActivityFeeds changeStatus(ActivityServiceStepActivityFeeds activityFeed) {
        activityFeed.setActivityFeedsStatus(ActivityFeedsStatus.CONSUMED);
        return activityFeed;
    }

    public List<UUID> getServiceStepsForActivity(Activity activity){
        return repository.findDistinctIdByActivity(activity);
    }
}
