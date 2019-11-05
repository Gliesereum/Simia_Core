package com.simia.expert.model.repository.jpa.business;

import com.simia.expert.model.entity.business.BaseBusinessEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;
import com.simia.share.common.repository.refreshable.RefreshableRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 * @since 12/7/18
 */
public interface BaseBusinessRepository extends AuditableRepository<BaseBusinessEntity>, RefreshableRepository, BusinessLockableRepository {

    boolean existsByIdAndCorporationIdInAndObjectState(UUID id, List<UUID> corporationIds, ObjectState objectState);

    List<BaseBusinessEntity> findByCorporationIdInAndObjectState(List<UUID> corporationIds, ObjectState objectState);

    List<BaseBusinessEntity> findByCorporationIdInAndObjectStateOrderByCreateDateDesc(List<UUID> corporationIds, ObjectState objectState);

    List<BaseBusinessEntity> findByCorporationIdAndObjectState(UUID corporationId, ObjectState objectState);

    List<BaseBusinessEntity> findByIdInAndObjectState(List<UUID> ids, ObjectState objectState);

    List<BaseBusinessEntity> getAllByObjectState(ObjectState objectState);

    List<BaseBusinessEntity> getAllByIdInAndObjectState(Iterable<UUID> ids, ObjectState objectState);

    @Query("SELECT DISTINCT b.id FROM BaseBusinessEntity b WHERE b.corporationId IN :corporationIds")
    List<UUID> getIdsByCorporationIdIn(@Param("corporationIds") List<UUID> corporationIds);

    @Query("SELECT DISTINCT b.id FROM BaseBusinessEntity b WHERE b.corporationId IN :corporationIds AND b.objectState = :objectState")
    List<UUID> getIdsByCorporationIdInAndObjectState(@Param("corporationIds") List<UUID> corporationIds, @Param("objectState") ObjectState objectState);
}
