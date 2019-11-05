package com.simia.expert.service.location.impl;

import com.simia.expert.model.entity.location.DistrictEntity;
import com.simia.expert.model.repository.jpa.location.DistrictRepository;
import com.simia.expert.service.location.CityService;
import com.simia.expert.service.location.DistrictService;
import com.simia.expert.service.location.GeoPositionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.expert.location.DistrictDto;
import com.simia.share.common.model.dto.expert.location.GeoPositionDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.KarmaExceptionMessage.*;


@Slf4j
@Service
public class DistrictServiceImpl extends DefaultServiceImpl<DistrictDto, DistrictEntity> implements DistrictService {

    private static final Class<DistrictDto> DTO_CLASS = DistrictDto.class;
    private static final Class<DistrictEntity> ENTITY_CLASS = DistrictEntity.class;

    private final DistrictRepository districtRepository;

    @Autowired
    private GeoPositionService geoPositionService;

    @Autowired
    private CityService cityService;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, DefaultConverter defaultConverter) {
        super(districtRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.districtRepository = districtRepository;
    }

    @Override
    @Transactional
    public DistrictDto create(DistrictDto dto) {
        checkCity(dto.getCityId());
        return super.create(dto);
    }

    @Override
    @Transactional
    public DistrictDto update(DistrictDto dto) {
        checkCity(dto.getCityId());
        return super.update(dto);
    }


    @Override
    public DistrictDto addGeoPosition(List<GeoPositionDto> positions, UUID id) {
        DistrictDto result = getById(id);
        if (result == null) {
            throw new ClientException(DISTRICT_NOT_FOUND);
        }
        if (CollectionUtils.isNotEmpty(positions)) {
            positions.forEach(f-> f.setObjectId(result.getId()));
            List<GeoPositionDto> resultPosition = geoPositionService.create(positions);
            result.setPolygonPoints(resultPosition);
        }
        return result;
    }

    @Override
    public List<DistrictDto> getAllByCityId(UUID cityId) {
        checkCity(cityId);
        List<DistrictEntity> entities = districtRepository.getAllByCityId(cityId);
        return converter.convert(entities, dtoClass);
    }

    private void checkCity(UUID id){
        if (!cityService.isExist(id)) {
            throw new ClientException(CITY_NOT_FOUND);
        }
    }
}
