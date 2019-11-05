package com.simia.expert.service.filter.impl;

import com.simia.expert.model.entity.filter.PriceFilterAttributeEntity;
import com.simia.expert.model.repository.jpa.filter.PriceFilterAttributeRepository;
import com.simia.expert.service.filter.PriceFilterAttributeService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.filter.PriceFilterAttributeDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class PriceFilterAttributeServiceImpl extends DefaultServiceImpl<PriceFilterAttributeDto, PriceFilterAttributeEntity> implements PriceFilterAttributeService {

    private static final Class<PriceFilterAttributeDto> DTO_CLASS = PriceFilterAttributeDto.class;
    private static final Class<PriceFilterAttributeEntity> ENTITY_CLASS = PriceFilterAttributeEntity.class;

    private final PriceFilterAttributeRepository priceFilterAttributeRepository;

    @Autowired
    public PriceFilterAttributeServiceImpl(PriceFilterAttributeRepository priceFilterAttributeRepository, DefaultConverter defaultConverter) {
        super(priceFilterAttributeRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.priceFilterAttributeRepository = priceFilterAttributeRepository;
    }

    @Override
    @Transactional
    public void deleteByPriceIdAndFilterId(UUID idPrice, UUID filterAttributeId) {
        priceFilterAttributeRepository.deleteByPriceIdAndFilterAttributeId(idPrice, filterAttributeId);
    }

    @Override
    public void deleteByPriceId(UUID idPrice) {
        priceFilterAttributeRepository.deleteByPriceId(idPrice);
    }

    @Override
    public boolean existByPriceIdAndAttributeId(UUID idPrice, UUID filterAttributeId) {
        return priceFilterAttributeRepository.existsByPriceIdAndFilterAttributeId(idPrice, filterAttributeId);
    }

}
