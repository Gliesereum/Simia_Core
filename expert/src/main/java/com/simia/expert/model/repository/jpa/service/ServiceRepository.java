package com.simia.expert.model.repository.jpa.service;

import com.simia.expert.model.entity.service.ServiceEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface ServiceRepository extends JpaRepository<ServiceEntity, UUID> {

    List<ServiceEntity> getAllByObjectState(ObjectState objectState);

    List<ServiceEntity> getAllByBusinessCategoryIdAndObjectStateOrderByName(UUID businessCategoryId, ObjectState state);

    ServiceEntity findByIdAndObjectState(UUID id, ObjectState objectState);

    List<ServiceEntity> getAllByIdInAndObjectState(Iterable<UUID> ids, ObjectState objectState);
}
