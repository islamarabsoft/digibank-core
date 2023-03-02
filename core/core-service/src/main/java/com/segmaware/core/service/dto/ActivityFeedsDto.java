package com.segmaware.core.service.dto;

import lombok.*;

import java.util.Map;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityFeedsDto {
    private UUID activityId;
    private Map<String, String> attributes;
}
