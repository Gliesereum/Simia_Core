package com.simia.expert.service.location;

import com.simia.expert.model.entity.location.DistrictEntity;
import com.simia.share.common.model.dto.expert.location.DistrictDto;
import com.simia.share.common.model.dto.expert.location.GeoPositionDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

public interface DistrictService extends DefaultService<DistrictDto, DistrictEntity> {

    DistrictDto addGeoPosition(List<GeoPositionDto> positions, UUID id);

    List<DistrictDto> getAllByCityId(UUID cityId);
}
