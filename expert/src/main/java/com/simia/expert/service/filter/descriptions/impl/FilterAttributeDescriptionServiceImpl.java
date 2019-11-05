package com.simia.expert.service.filter.descriptions.impl;

import com.simia.expert.model.entity.filter.descriptions.FilterAttributeDescriptionEntity;
import com.simia.expert.model.repository.jpa.filter.descriptions.FilterAttributeDescriptionRepository;
import com.simia.expert.service.filter.descriptions.FilterAttributeDescriptionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.filter.descriptions.FilterAttributeDescriptionDto;
import com.simia.share.common.service.descrption.impl.DefaultDescriptionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class FilterAttributeDescriptionServiceImpl extends DefaultDescriptionServiceImpl<FilterAttributeDescriptionDto, FilterAttributeDescriptionEntity> implements FilterAttributeDescriptionService {

    private static final Class<FilterAttributeDescriptionDto> DTO_CLASS = FilterAttributeDescriptionDto.class;
    private static final Class<FilterAttributeDescriptionEntity> ENTITY_CLASS = FilterAttributeDescriptionEntity.class;

    private final FilterAttributeDescriptionRepository filterAttributeDescriptionRepository;

    @Autowired
    public FilterAttributeDescriptionServiceImpl(FilterAttributeDescriptionRepository filterAttributeDescriptionRepository, DefaultConverter defaultConverter) {
        super(filterAttributeDescriptionRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.filterAttributeDescriptionRepository = filterAttributeDescriptionRepository;
    }
}
