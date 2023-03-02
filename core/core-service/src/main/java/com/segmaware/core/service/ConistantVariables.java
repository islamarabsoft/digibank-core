package com.segmaware.core.service;

import com.segmaware.core.dataaccess.entity.ActivityStatus;

import java.util.Arrays;
import java.util.List;

public interface ConistantVariables {
    List<ActivityStatus> ACTIVE_ACTIVITIES_STATUSES =
            Arrays.asList(ActivityStatus.READY, ActivityStatus.PROCESSING);
}
