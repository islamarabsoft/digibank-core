package com.segmaware.core.dataaccess.repo;

import com.segmaware.core.dataaccess.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public interface ActivityServiceStepActivityFeedsRepository extends
        JpaRepository<ActivityServiceStepActivityFeeds, UUID> {
    int countByUsernameAndActivityAndServiceStepAndActivityFeedsStatus(@NonNull String username,
            @NonNull Activity activity, @NonNull ServiceStep serviceStep,
                                                   @NonNull ActivityFeedsStatus activityFeedsStatus);

    List<ActivityServiceStepActivityFeeds>
    findByUsernameAndActivityFeedsStatusAndActivity_StatusIn(@NonNull String username,
                       @NonNull ActivityFeedsStatus activityFeedsStatus, @NonNull List<ActivityStatus> statuses);

    List<ActivityServiceStepActivityFeeds>
    findByUsernameAndActivityFeedsStatusAndActivity_StatusInAndServiceStep(
            @NonNull String username, @NonNull ActivityFeedsStatus activityFeedsStatus,
                            @NonNull List<ActivityStatus> statuses, @NonNull ServiceStep serviceStep);

    @Query("SELECT DISTINCT serviceStep.id FROM ActivityServiceStepActivityFeeds WHERE activity = :activity")
    List<UUID> findDistinctIdByActivity(@Param("activity") Activity activity);

}