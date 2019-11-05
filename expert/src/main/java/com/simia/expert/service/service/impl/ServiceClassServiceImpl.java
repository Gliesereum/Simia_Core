package com.simia.expert.service.service.impl;

import com.simia.expert.model.entity.service.ServiceClassEntity;
import com.simia.expert.model.repository.jpa.service.ServiceClassRepository;
import com.simia.expert.service.service.ServiceClassService;
import com.simia.expert.service.service.descriptions.ServiceClassDescriptionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.service.ServiceClassDto;
import com.simia.share.common.model.dto.expert.service.descriptions.ServiceClassDescriptionDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 * @since 12/5/18
 */
@Slf4j
@Service
public class ServiceClassServiceImpl extends DefaultServiceImpl<ServiceClassDto, ServiceClassEntity> implements ServiceClassService {

    private static final Class<ServiceClassDto> DTO_CLASS = ServiceClassDto.class;
    private static final Class<ServiceClassEntity> ENTITY_CLASS = ServiceClassEntity.class;

    private final ServiceClassRepository serviceClassRepository;

    @Autowired
    private ServiceClassDescriptionService serviceClassDescriptionService;

    @Autowired
    public ServiceClassServiceImpl(ServiceClassRepository serviceClassRepository, DefaultConverter defaultConverter) {
        super(serviceClassRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.serviceClassRepository = serviceClassRepository;
    }

    @Override
    public boolean existsService(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public ServiceClassDto create(ServiceClassDto dto) {
        ServiceClassDto result = null;
        if (dto != null) {
            result = super.create(dto);
            List<ServiceClassDescriptionDto> descriptions = serviceClassDescriptionService.create(dto.getDescriptions(), result.getId());
            result.setDescriptions(descriptions);
        }
        return result;
    }

    @Override
    public ServiceClassDto update(ServiceClassDto dto) {
        ServiceClassDto result = null;
        if (dto != null) {
            result = super.update(dto);
            List<ServiceClassDescriptionDto> descriptions = serviceClassDescriptionService.update(dto.getDescriptions(), result.getId());
            result.setDescriptions(descriptions);
        }
        return result;
    }
}
