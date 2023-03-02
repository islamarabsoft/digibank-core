package com.segmaware.core.dataaccess.entity;

import com.segmaware.common.dataaccess.entity.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ServiceCatalog extends BaseModel<UUID> {
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    @OrderBy("name")
    private Channel channel;

    @OneToMany(mappedBy = "serviceCatalog")
    private List<ServiceStep> serviceSteps;
}
