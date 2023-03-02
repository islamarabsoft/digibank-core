package com.segmaware.core.dataaccess.entity;

import com.segmaware.common.dataaccess.entity.BaseModel;
import lombok.*;

import javax.persistence.Entity;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Channel extends BaseModel<UUID> {
    private String name;
    private String description;
}
