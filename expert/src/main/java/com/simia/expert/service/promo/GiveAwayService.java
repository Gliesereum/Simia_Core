package com.simia.expert.service.promo;

import com.simia.expert.model.entity.promo.GiveAwayEntity;
import com.simia.share.common.model.dto.expert.promo.GiveAwayDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.UUID;

public interface GiveAwayService extends AuditableService<GiveAwayDto, GiveAwayEntity> {

    void participateInGiveAwayId(UUID id);

    void refuseParticipateInGiveAwayId(UUID id);
}