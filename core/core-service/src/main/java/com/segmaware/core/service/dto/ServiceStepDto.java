package com.segmaware.core.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class ServiceStepDto {
    private UUID stepId;
    private String name;
    private String description;
    private String stepKey;
    private String prefix;

    private int stepOrder;
}
