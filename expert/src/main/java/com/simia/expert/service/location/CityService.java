package com.simia.expert.service.location;

import com.simia.expert.model.entity.location.CityEntity;
import com.simia.share.common.model.dto.expert.location.CityDto;
import com.simia.share.common.model.dto.expert.location.GeoPositionDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

public interface CityService extends DefaultService<CityDto, CityEntity> {

    CityDto addGeoPosition(List<GeoPositionDto> positions, UUID id);
}
