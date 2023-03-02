package com.segmaware.core.service.dto;

import lombok.*;
import org.springframework.lang.NonNull;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDto {
    @NonNull
    private UUID serviceId;

}
