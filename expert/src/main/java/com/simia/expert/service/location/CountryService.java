package com.simia.expert.service.location;

import com.simia.expert.model.entity.location.CountryEntity;
import com.simia.share.common.model.dto.expert.location.CountryDto;
import com.simia.share.common.model.dto.expert.location.GeoPositionDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

public interface CountryService extends DefaultService<CountryDto, CountryEntity> {

    CountryDto addGeoPosition(List<GeoPositionDto> positions, UUID id);
}
