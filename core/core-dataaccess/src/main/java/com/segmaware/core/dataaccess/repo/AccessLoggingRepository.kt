package com.segmaware.core.dataaccess.repo;

import com.segmaware.core.dataaccess.entity.AccessLogging
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AccessLoggingRepository : JpaRepository<AccessLogging, UUID> {
}