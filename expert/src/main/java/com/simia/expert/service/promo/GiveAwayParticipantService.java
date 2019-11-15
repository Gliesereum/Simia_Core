package com.simia.expert.service.promo;

import com.simia.expert.model.entity.promo.GiveAwayParticipantEntity;
import com.simia.share.common.model.dto.expert.promo.GiveAwayParticipantDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.UUID;

public interface GiveAwayParticipantService extends AuditableService<GiveAwayParticipantDto, GiveAwayParticipantEntity> {

    GiveAwayParticipantDto getByUserIdAndGiveAwayId(UUID userId, UUID giveAwayId);
}