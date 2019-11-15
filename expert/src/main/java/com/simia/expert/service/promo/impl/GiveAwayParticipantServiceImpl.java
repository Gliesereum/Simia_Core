package com.simia.expert.service.promo.impl;

import com.simia.expert.model.entity.promo.GiveAwayParticipantEntity;
import com.simia.expert.model.repository.jpa.promo.GiveAwayParticipantRepository;
import com.simia.expert.service.promo.GiveAwayParticipantService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.promo.GiveAwayParticipantDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class GiveAwayParticipantServiceImpl extends AuditableServiceImpl<GiveAwayParticipantDto, GiveAwayParticipantEntity> implements GiveAwayParticipantService {

    private static final Class<GiveAwayParticipantDto> DTO_CLASS = GiveAwayParticipantDto.class;
    private static final Class<GiveAwayParticipantEntity> ENTITY_CLASS = GiveAwayParticipantEntity.class;

    private final GiveAwayParticipantRepository giveAwayParticipantRepository;

    @Autowired
    public GiveAwayParticipantServiceImpl(GiveAwayParticipantRepository giveAwayParticipantRepository, DefaultConverter defaultConverter) {
        super(giveAwayParticipantRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.giveAwayParticipantRepository = giveAwayParticipantRepository;
    }
    
}
