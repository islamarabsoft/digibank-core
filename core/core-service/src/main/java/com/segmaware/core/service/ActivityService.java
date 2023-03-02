package com.segmaware.core.service;

import com.segmaware.core.service.dto.ActivityDto;
import com.segmaware.core.service.dto.ActivityFeedsDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ActivityService {
    UUID addActivity(ActivityDto activityDto, String username);

    List<UUID> addActivityFeeds(UUID serviceStepId, UUID activityId,
                                Map<String, Object> activityDetailParam, String name);
    ActivityFeedsDto consumeActivityFeedsByActivity(String username);
    ActivityFeedsDto consumeActivityFeedsByActivityAndServiceStep
            (String username, UUID serviceStepId);
    ActivityFeedsDto consumeActivityFeedsByActivityAndServiceStep
            (String username, String serviceStepKey);
}
