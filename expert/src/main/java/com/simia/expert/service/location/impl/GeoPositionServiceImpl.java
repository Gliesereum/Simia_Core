package com.simia.expert.service.location.impl;

import com.simia.expert.model.entity.location.GeoPositionEntity;
import com.simia.expert.model.repository.jpa.location.GeoPositionRepository;
import com.simia.expert.service.location.GeoPositionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.location.GeoPositionDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GeoPositionServiceImpl extends DefaultServiceImpl<GeoPositionDto, GeoPositionEntity> implements GeoPositionService {

    private static final Class<GeoPositionDto> DTO_CLASS = GeoPositionDto.class;
    private static final Class<GeoPositionEntity> ENTITY_CLASS = GeoPositionEntity.class;

    private final GeoPositionRepository geoPositionRepository;

    @Autowired
    public GeoPositionServiceImpl(GeoPositionRepository geoPositionRepository, DefaultConverter defaultConverter) {
        super(geoPositionRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.geoPositionRepository = geoPositionRepository;
    }
}
