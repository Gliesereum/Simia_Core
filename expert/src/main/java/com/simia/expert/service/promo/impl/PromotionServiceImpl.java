package com.simia.expert.service.promo.impl;

import com.simia.expert.model.entity.promo.PromotionEntity;
import com.simia.expert.model.repository.jpa.promo.PromotionRepository;
import com.simia.expert.service.promo.PromotionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.promo.PromotionDto;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class PromotionServiceImpl extends AuditableServiceImpl<PromotionDto, PromotionEntity> implements PromotionService {

    private static final Class<PromotionDto> DTO_CLASS = PromotionDto.class;
    private static final Class<PromotionEntity> ENTITY_CLASS = PromotionEntity.class;

    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionRepository, DefaultConverter defaultConverter) {
        super(promotionRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.promotionRepository = promotionRepository;
    }

}