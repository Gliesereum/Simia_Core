package com.simia.expert.model.repository.jpa.promo;

import com.simia.expert.model.entity.promo.PromotionEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.List;
import java.util.UUID;

public interface PromotionRepository extends AuditableRepository<PromotionEntity> {

    List<PromotionEntity> getAllByCategoryIdAndObjectState(UUID id, ObjectState state);

    List<PromotionEntity> getAllByAuthorIdAndObjectState(UUID id, ObjectState state);

}