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
@Table(indexes = {
        @Index(unique = true, columnList = "service_catalog_id, step_key, step_order"),
        @Index(unique = true, columnList = "service_catalog_id, step_order")
})
public class ServiceStep extends BaseModel<UUID> {
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(name = "step_key", columnDefinition = "char(5)", nullable = false)
    private String stepKey;
    @Column(columnDefinition = "char(5)", nullable = false)
    private String prefix;
    @Column(name = "step_order", nullable = false)
    private int stepOrder;
    @ManyToOne
    @JoinColumn(name = "service_catalog_id", nullable = false)
    private ServiceCatalog serviceCatalog;
}
