package com.segmaware.core.service.impl;

import com.segmaware.core.dataaccess.entity.*;
import com.segmaware.core.service.ActivityService;
import com.segmaware.core.service.domain.ActivityDomain;
import com.segmaware.core.service.domain.ActivityServiceStepActivityFeedsDomain;
import com.segmaware.core.service.domain.ServiceCatalogDomain;
import com.segmaware.core.service.domain.ServiceStepDomain;
import com.segmaware.core.service.dto.ActivityDto;
import com.segmaware.core.service.dto.ActivityFeedsDto;
import com.segmaware.utility.api.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ServiceCatalogDomain serviceCatalogDomain;
    private final ActivityDomain activityDomain;
    private final ServiceStepDomain serviceStepDomain;
    private final ActivityServiceStepActivityFeedsDomain activityServiceStepActivityFeedsDomain;

    @Override
    public UUID addActivity(ActivityDto activityDto, String username) {
        //Check if ready or partially consumed activities opened with the same user
        if(activityDomain.checkActivityExistWithSameUser(username))
            throw new BadRequestException("User already has proceed with customer" +
                                    ", need to finalized or decline to start new activity");

        return activityDomain.addActivity(
                serviceCatalogDomain.getEntity(
                        activityDto.getServiceId()), username).getId();
    }

    @Override
    public List<UUID> addActivityFeeds(
            UUID serviceStepId, UUID activityId,
            Map<String, Object> activityFeedsParam, String username) {

        ServiceStep serviceStep = serviceStepDomain.getEntity(serviceStepId);
        Activity activity = activityDomain.getEntity(activityId);

        //Check if active activity feeds for the same step
        if(activityServiceStepActivityFeedsDomain.countOfActiveActivityFeedsForUser(activity, serviceStep, username) > 0)
            throw new BadRequestException("Having active activity feeds for the same step, please consume it or cancel the current activity");

        List<UUID> uuids = new ArrayList<>();
        activityFeedsParam.forEach((attribute, value) ->
                uuids.add(activityServiceStepActivityFeedsDomain.save(ActivityServiceStepActivityFeeds.builder()
                .activityFeedsStatus(ActivityFeedsStatus.ACTIVE)
                .serviceStep(serviceStep)
                .activity(activity)
                .username(username)
                .value((String) value)
                .attribute(attribute)
                .build()).getId()));

        return uuids;
    }

    @Override
    @Transactional
    public ActivityFeedsDto consumeActivityFeedsByActivity(String username){
        Activity activity = activityDomain.activeActivityByUsername(username);

        List<ActivityServiceStepActivityFeeds> activityServiceStepActivityFeeds
                = activityServiceStepActivityFeedsDomain.getActiveActivityFeedsForUser(username);

        activityDomain.changeStatus(activity);
        activityDomain.save(activity);

        return ActivityFeedsDto.builder()
                .attributes(getActivityFeedsAttributes(activityServiceStepActivityFeeds))
                .activityId(activity.getId())
                .build();
    }

    @Override
    @Transactional
    public ActivityFeedsDto consumeActivityFeedsByActivityAndServiceStep
            (String username, UUID serviceStepId){

        Activity activity = activityDomain.activeActivityByUsername(username);

        List<ActivityServiceStepActivityFeeds> activityServiceStepActivityFeeds
                = activityServiceStepActivityFeedsDomain
                    .getActivityFeedsByServiceStepForUser(
                        serviceStepDomain.getEntity(serviceStepId), username);

        activityDomain.changeStatus(activity);
        activityDomain.save(activity);

        return ActivityFeedsDto.builder()
                .attributes(getActivityFeedsAttributes(activityServiceStepActivityFeeds))
                .activityId(activity.getId())
                .build();
    }

    @Override
    @Transactional
    public ActivityFeedsDto consumeActivityFeedsByActivityAndServiceStep
            (String username, String serviceStepKey){
        Activity activity = activityDomain.activeActivityByUsername(username);

        List<ActivityServiceStepActivityFeeds> activityServiceStepActivityFeeds
                = activityServiceStepActivityFeedsDomain
                        .getActivityFeedsByServiceStepForUser(
                                serviceStepDomain.getEntityByKey(serviceStepKey), username);

        activityDomain.changeStatus(activity);
        activityDomain.save(activity);

        return ActivityFeedsDto.builder()
                .attributes(getActivityFeedsAttributes(activityServiceStepActivityFeeds))
                .activityId(activity.getId())
                .build();
    }

    private Map<String, String> getActivityFeedsAttributes
            (List<ActivityServiceStepActivityFeeds> activityServiceStepActivityFeeds){
        Map<String, String> activityFeedsAttributes = new HashMap<>();

        activityServiceStepActivityFeeds
                .forEach(activityFeed ->
                        {
                            activityFeedsAttributes.put(activityFeed.getAttribute(),
                                    activityFeed.getValue());

                            activityServiceStepActivityFeedsDomain.changeStatus(activityFeed);

                            activityServiceStepActivityFeedsDomain.save(activityFeed);
                        }
                );

        return activityFeedsAttributes;
    }
}
