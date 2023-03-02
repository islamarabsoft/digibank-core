package com.segmaware.core.service.mapper;

import com.segmaware.common.service.mapper.IEntityMapper;
import com.segmaware.core.dataaccess.entity.AccessLogging;
import com.segmaware.core.service.dto.UserInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface UserAccessMapper extends IEntityMapper<UserInfoDto, AccessLogging> {
}
