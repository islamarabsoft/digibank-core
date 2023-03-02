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
public class AccessLogging extends BaseModel<UUID> {
    private String userId;
    private String userName;
    private String ip;
    private long authTime;
    private String sessionId;
    private String issuer;

}
