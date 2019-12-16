package com.simia.expert.model.repository.jpa.promo;

import com.simia.expert.model.entity.promo.GiveAwayParticipantEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.UUID;

public interface GiveAwayParticipantRepository extends AuditableRepository<GiveAwayParticipantEntity> {

    GiveAwayParticipantEntity getByUserIdAndGiveAwayIdAndObjectState(UUID userId, UUID giveAwayId, ObjectState state);
}