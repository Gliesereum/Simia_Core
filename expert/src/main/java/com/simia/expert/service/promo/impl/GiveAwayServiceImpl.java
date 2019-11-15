package com.simia.expert.service.promo.impl;

import com.simia.expert.model.entity.promo.GiveAwayEntity;
import com.simia.expert.model.repository.jpa.promo.GiveAwayRepository;
import com.simia.expert.service.promo.GiveAwayParticipantService;
import com.simia.expert.service.promo.GiveAwayService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.CustomException;
import com.simia.share.common.model.dto.expert.promo.GiveAwayDto;
import com.simia.share.common.model.dto.expert.promo.GiveAwayParticipantDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import com.simia.share.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.simia.share.common.exception.messages.ExpertExceptionMessage.GIVE_AWAY_NOT_FOUND;
import static com.simia.share.common.exception.messages.ExpertExceptionMessage.GIVE_AWAY_TIME_EXPIRED;


@Slf4j
@Service
public class GiveAwayServiceImpl extends AuditableServiceImpl<GiveAwayDto, GiveAwayEntity> implements GiveAwayService {

    private static final Class<GiveAwayDto> DTO_CLASS = GiveAwayDto.class;
    private static final Class<GiveAwayEntity> ENTITY_CLASS = GiveAwayEntity.class;

    private final GiveAwayRepository giveAwayRepository;

    @Autowired
    public GiveAwayServiceImpl(GiveAwayRepository giveAwayRepository, DefaultConverter defaultConverter) {
        super(giveAwayRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.giveAwayRepository = giveAwayRepository;
    }

    @Autowired
    private GiveAwayParticipantService participantService;

   

    private UUID getUserId() {
        SecurityUtil.checkUserByBanStatus();
        return SecurityUtil.getUserId();
    }
}
