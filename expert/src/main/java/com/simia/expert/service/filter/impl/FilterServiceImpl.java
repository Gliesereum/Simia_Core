package com.simia.expert.service.filter.impl;

import com.simia.expert.model.entity.filter.FilterEntity;
import com.simia.expert.model.repository.jpa.filter.FilterRepository;
import com.simia.expert.service.business.BusinessCategoryService;
import com.simia.expert.service.filter.FilterService;
import com.simia.expert.service.filter.descriptions.FilterDescriptionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.business.BusinessCategoryDto;
import com.simia.share.common.model.dto.expert.enumerated.BusinessType;
import com.simia.share.common.model.dto.expert.filter.FilterDto;
import com.simia.share.common.model.dto.expert.filter.descriptions.FilterDescriptionDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class FilterServiceImpl extends DefaultServiceImpl<FilterDto, FilterEntity> implements FilterService {

    private static final Class<FilterDto> DTO_CLASS = FilterDto.class;
    private static final Class<FilterEntity> ENTITY_CLASS = FilterEntity.class;

    private final FilterRepository filterRepository;

    @Autowired
    private BusinessCategoryService businessCategoryService;

    @Autowired
    private FilterDescriptionService filterDescriptionService;

    @Autowired
    public FilterServiceImpl(FilterRepository filterRepository, DefaultConverter defaultConverter) {
        super(filterRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.filterRepository = filterRepository;
    }

    @Override
    public FilterDto create(FilterDto dto) {
        FilterDto result = null;
        if (dto != null) {
            result = super.create(dto);
            List<FilterDescriptionDto> descriptions = filterDescriptionService.create(dto.getDescriptions(), result.getId());
            result.setDescriptions(descriptions);
        }
        return result;
    }

    @Override
    public FilterDto update(FilterDto dto) {
        FilterDto result = null;
        if (dto != null) {
            result = super.update(dto);
            List<FilterDescriptionDto> descriptions = filterDescriptionService.update(dto.getDescriptions(), result.getId());
            result.setDescriptions(descriptions);
        }
        return result;
    }

    @Override
    public List<FilterDto> getAllByBusinessCategoryId(UUID businessCategoryId) {
        List<FilterEntity> entities = filterRepository.findAllByBusinessCategoryId(businessCategoryId);
        return converter.convert(entities, dtoClass);
    }

    @Override
    public List<FilterDto> getAllByBusinessType(BusinessType businessType) {
        List<FilterDto> result = null;
        if (businessType != null) {
            List<BusinessCategoryDto> businessCategories = businessCategoryService.getByBusinessType(businessType);
            if (CollectionUtils.isNotEmpty(businessCategories)) {
                List<UUID> ids = businessCategories.stream()
                        .map(BusinessCategoryDto::getId)
                        .collect(Collectors.toList());
                List<FilterEntity> entities = filterRepository.findAllByBusinessCategoryIdIn(ids);
                result = converter.convert(entities, dtoClass);
            }
        }
        return result;
    }
}
