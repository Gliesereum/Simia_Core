package com.simia.expert.model.repository.jpa.promo;

import com.simia.expert.model.entity.promo.InterestedUserEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.List;
import java.util.UUID;

public interface InterestedUserRepository extends AuditableRepository<InterestedUserEntity> {

    InterestedUserEntity getByPromotionIdAndUserIdAndObjectState(UUID promotionId, UUID userId, ObjectState state);

    List<InterestedUserEntity> getByPromotionIdAndObjectState(UUID promotionId, ObjectState state);

    List<InterestedUserEntity> getByUserIdAndObjectState(UUID userId, ObjectState state);
}