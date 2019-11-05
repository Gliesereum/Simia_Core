package com.simia.expert.model.repository.jpa.service;

import com.simia.expert.model.entity.service.ServicePriceEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.refreshable.RefreshableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface ServicePriceRepository extends JpaRepository<ServicePriceEntity, UUID>, RefreshableRepository {

    List<ServicePriceEntity> findAllByBusinessIdAndObjectState(UUID id, ObjectState objectState);

    List<ServicePriceEntity> findAllByBusinessIdInAndObjectState(List<UUID> ids, ObjectState objectState);

    List<ServicePriceEntity> getAllByObjectState(ObjectState objectState);

    List<ServicePriceEntity> getAllByIdInAndObjectState(Iterable<UUID> ids, ObjectState objectState);

    ServicePriceEntity findByIdAndObjectState(UUID id, ObjectState objectState);

    int countAllByBusinessIdAndIdIn(UUID businessId, List<UUID> ids);

}
