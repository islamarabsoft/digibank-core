package com.segmaware.core.dataaccess.entity;

import com.segmaware.common.dataaccess.entity.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ActivityServiceStepActivityFeeds extends BaseModel<UUID> {

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String username;
    private String attribute;
    @Column(columnDefinition = "nvarchar(max)")
    private String value;
    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
    @ManyToOne
    @JoinColumn(name = "service_step_id")
    private ServiceStep serviceStep;
    @Enumerated(EnumType.ORDINAL)
    private ActivityFeedsStatus activityFeedsStatus;
}
