package com.segmaware.common.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Setter
@Getter
@MappedSuperclass
public abstract class BaseModel <ID> implements Serializable {
    @Id
    @Column(name = "id", nullable = false, columnDefinition = "uniqueidentifier")
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private ID id;
    @Basic(optional = false)
    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createdAt;

    @Basic(optional = false)
    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date lastModifiedAt;
}
