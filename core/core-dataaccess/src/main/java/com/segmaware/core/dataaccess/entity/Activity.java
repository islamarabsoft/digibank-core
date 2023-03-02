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
public class Activity extends BaseModel<UUID> {
    private String name;
    private String description;
    @Column(nullable = false)
    private String username;
    @Enumerated(EnumType.ORDINAL)
    private ActivityStatus status;
    @ManyToOne
    @JoinColumn(name = "service_id")
    @OrderBy("name")
    private ServiceCatalog serviceCatalog;
}
