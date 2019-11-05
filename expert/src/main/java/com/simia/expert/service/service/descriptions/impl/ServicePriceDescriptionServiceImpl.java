package com.simia.expert.service.service.descriptions.impl;

import com.simia.expert.model.entity.service.descriptions.ServicePriceDescriptionEntity;
import com.simia.expert.model.repository.jpa.service.descriptions.ServicePriceDescriptionRepository;
import com.simia.expert.service.service.descriptions.ServicePriceDescriptionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.service.descriptions.ServicePriceDescriptionDto;
import com.simia.share.common.service.descrption.impl.DefaultDescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
public class ServicePriceDescriptionServiceImpl extends DefaultDescriptionServiceImpl<ServicePriceDescriptionDto, ServicePriceDescriptionEntity> implements ServicePriceDescriptionService {

    private static final Class<ServicePriceDescriptionDto> DTO_CLASS = ServicePriceDescriptionDto.class;
    private static final Class<ServicePriceDescriptionEntity> ENTITY_CLASS = ServicePriceDescriptionEntity.class;

    private final ServicePriceDescriptionRepository servicePriceDescriptionRepository;

    @Autowired
    public ServicePriceDescriptionServiceImpl(ServicePriceDescriptionRepository servicePriceDescriptionRepository, DefaultConverter defaultConverter) {
        super(servicePriceDescriptionRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.servicePriceDescriptionRepository = servicePriceDescriptionRepository;
    }
}
