package com.segmaware.common.service.domain;

import com.segmaware.utility.api.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
public abstract class BaseDomain <ID, Entity> {
    private final JpaRepository<Entity, ID> jpaRepository;

    public Entity save(Entity entity) {
        entity = jpaRepository.save(entity);
        return entity;
    }

    public Entity getEntity(ID id){
        return jpaRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException());
    }
}
