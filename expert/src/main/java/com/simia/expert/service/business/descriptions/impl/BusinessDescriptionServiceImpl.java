package com.simia.expert.service.business.descriptions.impl;

import com.simia.expert.model.entity.business.descriptions.BusinessDescriptionEntity;
import com.simia.expert.model.repository.jpa.business.descriptions.BusinessDescriptionRepository;
import com.simia.expert.service.business.descriptions.BusinessDescriptionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.business.descriptions.BusinessDescriptionDto;
import com.simia.share.common.service.descrption.impl.DefaultDescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
public class BusinessDescriptionServiceImpl extends DefaultDescriptionServiceImpl<BusinessDescriptionDto, BusinessDescriptionEntity> implements BusinessDescriptionService {

    private static final Class<BusinessDescriptionDto> DTO_CLASS = BusinessDescriptionDto.class;
    private static final Class<BusinessDescriptionEntity> ENTITY_CLASS = BusinessDescriptionEntity.class;

    private final BusinessDescriptionRepository businessDescriptionRepository;

    @Autowired
    public BusinessDescriptionServiceImpl(BusinessDescriptionRepository businessDescriptionRepository, DefaultConverter defaultConverter) {
        super(businessDescriptionRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.businessDescriptionRepository = businessDescriptionRepository;
    }
}
