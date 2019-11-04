package com.simia.permission.service.endpoint;

import com.simia.permission.model.entity.endpoint.EndpointEntity;
import com.simia.share.common.model.dto.base.enumerated.Method;
import com.simia.share.common.model.dto.permission.endpoint.EndpointDto;
import com.simia.share.common.model.dto.permission.endpoint.EndpointPackDto;
import com.simia.share.common.service.DefaultService;

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
