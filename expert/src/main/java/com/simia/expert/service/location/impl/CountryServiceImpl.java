package com.simia.expert.service.location.impl;

import com.simia.expert.model.entity.location.CountryEntity;
import com.simia.expert.model.repository.jpa.location.CountryRepository;
import com.simia.expert.service.location.CountryService;
import com.simia.expert.service.location.GeoPositionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.expert.location.CountryDto;
import com.simia.share.common.model.dto.expert.location.GeoPositionDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.KarmaExceptionMessage.COUNTRY_NOT_FOUND;

@Slf4j
@Service
public class CountryServiceImpl extends DefaultServiceImpl<CountryDto, CountryEntity> implements CountryService {

    private static final Class<CountryDto> DTO_CLASS = CountryDto.class;
    private static final Class<CountryEntity> ENTITY_CLASS = CountryEntity.class;

    private final CountryRepository countryRepository;

    @Autowired
    private GeoPositionService geoPositionService;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, DefaultConverter defaultConverter) {
        super(countryRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional
    public CountryDto addGeoPosition(List<GeoPositionDto> positions, UUID id) {
        CountryDto result = getById(id);
        if (result == null) {
            throw new ClientException(COUNTRY_NOT_FOUND);
        }
        if (CollectionUtils.isNotEmpty(positions)) {
            positions.forEach(f-> f.setObjectId(result.getId()));
            List<GeoPositionDto> resultPosition = geoPositionService.create(positions);
            result.setPolygonPoints(resultPosition);
        }
        return result;
    }
}
