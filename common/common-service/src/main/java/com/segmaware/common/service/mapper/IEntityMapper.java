package com.segmaware.common.service.mapper;

import java.util.List;

public interface IEntityMapper<D, E> {

   D toDto(final E e);

   E toEntity(final D d);

   List<D> toDto(final List<E> eList);

   List<E> toEntity(final List<D> dList);

}
