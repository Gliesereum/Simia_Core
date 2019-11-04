package com.gliesereum.permission.service.endpoint;

import com.gliesereum.permission.model.entity.endpoint.EndpointEntity;
import com.gliesereum.share.common.model.dto.base.enumerated.Method;
import com.gliesereum.share.common.model.dto.permission.endpoint.EndpointDto;
import com.gliesereum.share.common.model.dto.permission.endpoint.EndpointPackDto;
import com.gliesereum.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface EndpointService extends DefaultService<EndpointDto, EndpointEntity> {

    List<EndpointDto> createPack(EndpointPackDto endpointPack);

    EndpointDto getByUrlAndModuleId(String url, UUID moduleId);

    EndpointDto getByUrlAndModuleIdAndMethod(String url, UUID moduleId, Method method);
}
