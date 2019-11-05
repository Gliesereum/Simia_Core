package com.simia.expert.service.service.descriptions.impl;

import com.simia.expert.model.entity.service.descriptions.ServiceClassDescriptionEntity;
import com.simia.expert.model.repository.jpa.service.descriptions.ServiceClassDescriptionRepository;
import com.simia.expert.service.service.descriptions.ServiceClassDescriptionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.service.descriptions.ServiceClassDescriptionDto;
import com.simia.share.common.service.descrption.impl.DefaultDescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
public class ServiceClassDescriptionServiceImpl extends DefaultDescriptionServiceImpl<ServiceClassDescriptionDto, ServiceClassDescriptionEntity> implements ServiceClassDescriptionService {

    private static final Class<ServiceClassDescriptionDto> DTO_CLASS = ServiceClassDescriptionDto.class;
    private static final Class<ServiceClassDescriptionEntity> ENTITY_CLASS = ServiceClassDescriptionEntity.class;

    private final ServiceClassDescriptionRepository serviceClassDescriptionRepository;

    @Autowired
    public ServiceClassDescriptionServiceImpl(ServiceClassDescriptionRepository serviceClassDescriptionRepository, DefaultConverter defaultConverter) {
        super(serviceClassDescriptionRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.serviceClassDescriptionRepository = serviceClassDescriptionRepository;
    }
}
