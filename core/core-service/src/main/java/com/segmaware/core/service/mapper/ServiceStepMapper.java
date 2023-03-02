package com.segmaware.core.service.mapper;

import com.segmaware.common.service.mapper.IEntityMapper;
import com.segmaware.core.dataaccess.entity.ServiceStep;
import com.segmaware.core.service.dto.ServiceStepDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceStepMapper extends IEntityMapper<ServiceStepDto, ServiceStep> {
}
