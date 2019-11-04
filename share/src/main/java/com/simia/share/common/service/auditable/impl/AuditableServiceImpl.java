package com.simia.share.common.service.auditable.impl;

import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.AuditableDefaultDto;
import com.simia.share.common.model.entity.AuditableDefaultEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;
import com.simia.share.common.service.DefaultServiceImpl;
import com.simia.share.common.service.auditable.AuditableService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.simia.share.common.exception.messages.CommonExceptionMessage.NOT_EXIST_BY_ID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public class AuditableServiceImpl<D extends AuditableDefaultDto, E extends AuditableDefaultEntity> extends DefaultServiceImpl<D, E> implements AuditableService<D, E> {

    protected final AuditableRepository<E> auditableRepository;

    public AuditableServiceImpl(AuditableRepository<E> auditableRepository, DefaultConverter defaultConverter, Class<D> dtoClass, Class<E> entityClass) {
        super(auditableRepository, defaultConverter, dtoClass, entityClass);
        this.auditableRepository = auditableRepository;
    }

    @Override
    public D getByIdAndObjectState(UUID id, ObjectState objectState) {
        D result = null;
        if (ObjectUtils.allNotNull(id, objectState)) {
            Optional<E> entity = auditableRepository.findByIdAndObjectState(id, objectState);
            result = converter.convert(entity.orElse(null), dtoClass);
        }
        return result;
    }

    @Override
    public List<D> getByIds(Iterable<UUID> ids, ObjectState objectState) {
        List<D> result = null;
        if (ObjectUtils.allNotNull(ids, objectState)) {
            List<E> entites = auditableRepository.findByIdInAndObjectState(ids, objectState);
            result = converter.convert(entites, dtoClass);
        }
        return result;
    }

    @Override
    public List<D> getAll(ObjectState objectState) {
        List<D> result = null;
        if (objectState != null) {
            List<E> entities = auditableRepository.findByObjectState(objectState);
            result = converter.convert(entities, dtoClass);
        }
        return result;
    }

    @Override
    public Page<D> getAll(ObjectState objectState, Pageable pageable) {
        Page<D> result = null;
        if (ObjectUtils.allNotNull(objectState, pageable)) {
            Page<E> entities = auditableRepository.findAllByObjectState(objectState, pageable);
            result = converter.convert(entities, dtoClass);
        }
        return result;
    }

    @Override
    public boolean isExist(UUID id, ObjectState objectState) {
        return auditableRepository.existsByIdAndObjectState(id, objectState);
    }

    @Override
    public long count(ObjectState objectState) {
        return auditableRepository.countByObjectState(objectState);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (id != null) {
            E entity = repository
                    .findById(id)
                    .orElseThrow(() -> new ClientException(NOT_EXIST_BY_ID));
            entity.setObjectState(ObjectState.DELETED);
            entity.setDeleteDate(LocalDateTime.now(ZoneId.of("UTC")));
            auditableRepository.saveAndFlush(entity);
        }
    }

    @Override
    @Transactional
    public void delete(D dto) {
        E entity = converter.convert(dto, entityClass);
        if (entity == null) {
            throw new ClientException(NOT_EXIST_BY_ID);
        }
        entity.setObjectState(ObjectState.DELETED);
        entity.setDeleteDate(LocalDateTime.now(ZoneId.of("UTC")));
        auditableRepository.saveAndFlush(entity);
    }

    @Override
    protected E onCreate(E entity) {
        entity = super.onCreate(entity);
        entity.setObjectState(ObjectState.ACTIVE);
        entity.setDeleteDate(null);
        return entity;
    }

    @Override
    protected E onUpdate(E entity, E existed) {
        entity = super.onUpdate(entity, existed);
        entity.setDeleteDate(existed.getDeleteDate());
        entity.setObjectState(existed.getObjectState());
        entity.setCreateDate(existed.getCreateDate());
        return entity;
    }
}
