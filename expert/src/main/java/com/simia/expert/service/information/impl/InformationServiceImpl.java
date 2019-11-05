package com.simia.expert.service.information.impl;

import com.simia.expert.model.entity.information.InformationEntity;
import com.simia.expert.model.repository.jpa.information.InformationRepository;
import com.simia.expert.service.information.InformationService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.information.InformationDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Slf4j
@Service
public class InformationServiceImpl extends DefaultServiceImpl<InformationDto, InformationEntity> implements InformationService {

    private static final Class<InformationDto> DTO_CLASS = InformationDto.class;
    private static final Class<InformationEntity> ENTITY_CLASS = InformationEntity.class;

    private final InformationRepository informationRepository;

    @Autowired
    public InformationServiceImpl(InformationRepository informationRepository, DefaultConverter defaultConverter) {
        super(informationRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.informationRepository = informationRepository;
    }

    @Override
    public List<InformationDto> getAll() {
        List<InformationEntity> entities = informationRepository.findByOrderByIndex();
        return converter.convert(entities, dtoClass);
    }

    @Override
    public List<InformationDto> getByTag(String tag) {
        List<InformationEntity> entities = informationRepository.findByTagOrderByIndex(tag);
        return converter.convert(entities, dtoClass);
    }

    @Override
    public List<InformationDto> getByTagAndIsoCode(String tag, String isoCode) {
        List<InformationEntity> entities = informationRepository.findByTagAndIsoCodeOrderByIndex(tag, isoCode);
        return converter.convert(entities, dtoClass);
    }
}
