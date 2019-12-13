package com.simia.expert.service.promo.impl;

import com.simia.expert.model.entity.promo.InterestedUserEntity;
import com.simia.expert.model.repository.jpa.promo.InterestedUserRepository;
import com.simia.expert.service.promo.InterestedUserService;
import com.simia.expert.service.promo.PromotionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.CustomException;
import com.simia.share.common.model.dto.expert.promo.GiveAwayDto;
import com.simia.share.common.model.dto.expert.promo.InterestedUserDto;
import com.simia.share.common.model.dto.expert.promo.PromotionDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import com.simia.share.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.ExpertExceptionMessage.GIVE_AWAY_NOT_FOUND;
import static com.simia.share.common.exception.messages.ExpertExceptionMessage.PROMOTION_NOT_FOUND;


@Slf4j
@Service
public class InterestedUserServiceImpl extends AuditableServiceImpl<InterestedUserDto, InterestedUserEntity> implements InterestedUserService {

    private static final Class<InterestedUserDto> DTO_CLASS = InterestedUserDto.class;
    private static final Class<InterestedUserEntity> ENTITY_CLASS = InterestedUserEntity.class;

    private final InterestedUserRepository interestedUserRepository;

    @Autowired
    public InterestedUserServiceImpl(InterestedUserRepository interestedUserRepository, DefaultConverter defaultConverter) {
        super(interestedUserRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.interestedUserRepository = interestedUserRepository;
    }

    @Override
    public List<InterestedUserDto> getAllByUserId(UUID id) {
        List<InterestedUserEntity> entities = interestedUserRepository.getByUserIdAndObjectState(id, ObjectState.ACTIVE);
        return converter.convert(entities, dtoClass);
    }

    @Override
    public List<InterestedUserDto> getAllByPromotionId(UUID id) {
        List<InterestedUserEntity> entities = interestedUserRepository.getByPromotionIdAndObjectState(id, ObjectState.ACTIVE);
        return converter.convert(entities, dtoClass);
    }

    @Override
    public InterestedUserDto getByPromoIdAndUserId(UUID promotionId, UUID userId) {
        InterestedUserEntity entity = interestedUserRepository.getByPromotionIdAndUserIdAndObjectState(promotionId, userId, ObjectState.ACTIVE);
        return converter.convert(entity, dtoClass);
    }
}