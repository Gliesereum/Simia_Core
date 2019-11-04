package com.simia.permission.service.endpoint.impl;

import com.simia.permission.model.entity.endpoint.EndpointEntity;
import com.simia.permission.model.repository.jpa.endpoint.EndpointRepository;
import com.simia.permission.service.endpoint.EndpointService;
import com.simia.permission.service.module.ModuleService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.base.enumerated.Method;
import com.simia.share.common.model.dto.permission.endpoint.EndpointDto;
import com.simia.share.common.model.dto.permission.endpoint.EndpointPackDto;
import com.simia.share.common.model.dto.permission.module.ModuleDto;
import com.simia.share.common.service.DefaultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
@Service
public class EndpointServiceImpl extends DefaultServiceImpl<EndpointDto, EndpointEntity> implements EndpointService {

    private static final Class<EndpointDto> DTO_CLASS = EndpointDto.class;
    private static final Class<EndpointEntity> ENTITY_CLASS = EndpointEntity.class;

    private EndpointRepository endpointRepository;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    public EndpointServiceImpl(EndpointRepository endpointRepository, DefaultConverter defaultConverter) {
        super(endpointRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.endpointRepository = endpointRepository;
    }

    @Override
    public EndpointDto getByUrlAndModuleId(String url, UUID moduleId) {
        return converter.convert(endpointRepository.findByUrlAndModuleId(url, moduleId), dtoClass);
    }

    @Override
    public EndpointDto getByUrlAndModuleIdAndMethod(String url, UUID moduleId, Method method) {
        return converter.convert(endpointRepository.findByUrlAndModuleIdAndMethod(url, moduleId, method), dtoClass);
    }

    @Override
    public List<EndpointDto> createPack(EndpointPackDto endpointPack) {
        List<EndpointDto> result = null;
        if (endpointPack != null) {
            result = new ArrayList<>();
            ModuleDto module = moduleService.getByUrlOrCreate(endpointPack.getModuleUrl(), endpointPack.getModuleName());
            if (module != null) {
                UUID moduleId = module.getId();
                for (EndpointDto endpoint : endpointPack.getEndpoints()) {
                    EndpointDto exist = getByUrlAndModuleIdAndMethod(endpoint.getUrl(), moduleId, endpoint.getMethod());
                    if (exist == null) {
                        endpoint.setModuleId(moduleId);
                        result.add(super.create(endpoint));
                    }
                }
            }
        }
        return result;
    }
}
