package com.segmaware.core.dataaccess.repo;

import com.segmaware.core.dataaccess.entity.Activity;
import com.segmaware.core.dataaccess.entity.ActivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    Optional<Activity> findByUsernameIgnoreCaseAndStatusIn(String username, List<ActivityStatus> statuses);
    Optional<Activity> findByUsernameIgnoreCase(String username);

    long countByUsernameIgnoreCaseAndStatusIn(String username, Collection<ActivityStatus> statuses);
    long countByUsernameIgnoreCaseAndStatus(String username, ActivityStatus status);


}