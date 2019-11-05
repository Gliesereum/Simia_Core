package com.simia.expert.service.service.descriptions.impl;

import com.simia.expert.model.entity.service.descriptions.ServiceDescriptionEntity;
import com.simia.expert.model.repository.jpa.service.descriptions.ServiceDescriptionRepository;
import com.simia.expert.service.service.descriptions.ServiceDescriptionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.service.descriptions.ServiceDescriptionDto;
import com.simia.share.common.service.descrption.impl.DefaultDescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
public class ServiceDescriptionServiceImpl extends DefaultDescriptionServiceImpl<ServiceDescriptionDto, ServiceDescriptionEntity> implements ServiceDescriptionService {

    private static final Class<ServiceDescriptionDto> DTO_CLASS = ServiceDescriptionDto.class;
    private static final Class<ServiceDescriptionEntity> ENTITY_CLASS = ServiceDescriptionEntity.class;

    private final ServiceDescriptionRepository serviceDescriptionRepository;

    @Autowired
    public ServiceDescriptionServiceImpl(ServiceDescriptionRepository serviceDescriptionRepository, DefaultConverter defaultConverter) {
        super(serviceDescriptionRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.serviceDescriptionRepository = serviceDescriptionRepository;
    }
}
