package com.simia.expert.service.promo;

import com.simia.expert.model.entity.promo.InterestedUserEntity;
import com.simia.share.common.model.dto.expert.promo.InterestedUserDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.List;
import java.util.UUID;

public interface InterestedUserService extends AuditableService<InterestedUserDto, InterestedUserEntity> {

    InterestedUserDto getByPromoIdAndUserId(UUID promotionId, UUID userId);

    List<InterestedUserDto> getAllByUserId(UUID id);

    List<InterestedUserDto> getAllByPromotionId(UUID id);
}