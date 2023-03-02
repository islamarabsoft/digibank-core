package com.segmaware.core.dataaccess.repo;

import com.segmaware.core.dataaccess.entity.ServiceCatalog
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ServiceRepository: JpaRepository<ServiceCatalog, UUID> {
}