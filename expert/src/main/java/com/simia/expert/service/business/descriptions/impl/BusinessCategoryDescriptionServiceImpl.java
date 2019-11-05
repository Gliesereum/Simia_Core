package com.simia.expert.service.business.descriptions.impl;

import com.simia.expert.model.entity.business.descriptions.BusinessCategoryDescriptionEntity;
import com.simia.expert.model.repository.jpa.business.descriptions.BusinessCategoryDescriptionRepository;
import com.simia.expert.service.business.descriptions.BusinessCategoryDescriptionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.business.descriptions.BusinessCategoryDescriptionDto;
import com.simia.share.common.service.descrption.impl.DefaultDescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
public class BusinessCategoryDescriptionServiceImpl extends DefaultDescriptionServiceImpl<BusinessCategoryDescriptionDto, BusinessCategoryDescriptionEntity> implements BusinessCategoryDescriptionService {

    private static final Class<BusinessCategoryDescriptionDto> DTO_CLASS = BusinessCategoryDescriptionDto.class;
    private static final Class<BusinessCategoryDescriptionEntity> ENTITY_CLASS = BusinessCategoryDescriptionEntity.class;

    private final BusinessCategoryDescriptionRepository businessCategoryDescriptionRepository;

    @Autowired
    public BusinessCategoryDescriptionServiceImpl(BusinessCategoryDescriptionRepository businessCategoryDescriptionRepository, DefaultConverter defaultConverter) {
        super(businessCategoryDescriptionRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.businessCategoryDescriptionRepository = businessCategoryDescriptionRepository;
    }
}
