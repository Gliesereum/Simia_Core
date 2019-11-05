package com.simia.expert.service.location.impl;

import com.simia.expert.model.entity.location.CityEntity;
import com.simia.expert.model.repository.jpa.location.CityRepository;
import com.simia.expert.service.location.CityService;
import com.simia.expert.service.location.CountryService;
import com.simia.expert.service.location.GeoPositionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.expert.location.CityDto;
import com.simia.share.common.model.dto.expert.location.GeoPositionDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.KarmaExceptionMessage.CITY_NOT_FOUND;
import static com.simia.share.common.exception.messages.KarmaExceptionMessage.COUNTRY_NOT_FOUND;


@Slf4j
@Service
public class CityServiceImpl extends DefaultServiceImpl<CityDto, CityEntity> implements CityService {

    private static final Class<CityDto> DTO_CLASS = CityDto.class;
    private static final Class<CityEntity> ENTITY_CLASS = CityEntity.class;

    private final CityRepository cityRepository;

    @Autowired
    private GeoPositionService geoPositionService;

    @Autowired
    private CountryService countryService;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, DefaultConverter defaultConverter) {
        super(cityRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional
    public CityDto create(CityDto dto) {
        checkCountry(dto.getCountryId());
        return super.create(dto);
    }

    @Override
    @Transactional
    public CityDto update(CityDto dto) {
        checkCountry(dto.getCountryId());
        return super.update(dto);
    }

    @Override
    @Transactional
    public CityDto addGeoPosition(List<GeoPositionDto> positions, UUID id) {
        CityDto result = getById(id);
        if (result == null) {
            throw new ClientException(CITY_NOT_FOUND);
        }
        if (CollectionUtils.isNotEmpty(positions)) {
            positions.forEach(f-> f.setObjectId(result.getId()));
            List<GeoPositionDto> resultPosition = geoPositionService.create(positions);
            result.setPolygonPoints(resultPosition);
        }
        return result;
    }

    private void checkCountry(UUID id){
        if (!countryService.isExist(id)) {
            throw new ClientException(COUNTRY_NOT_FOUND);
        }
    }
}
