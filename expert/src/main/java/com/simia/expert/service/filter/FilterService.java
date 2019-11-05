package com.simia.expert.service.filter;

import com.simia.expert.model.entity.filter.FilterEntity;
import com.simia.share.common.model.dto.expert.enumerated.BusinessType;
import com.simia.share.common.model.dto.expert.filter.FilterDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface FilterService extends DefaultService<FilterDto, FilterEntity> {

    List<FilterDto> getAllByBusinessCategoryId(UUID businessCategoryId);

    List<FilterDto> getAllByBusinessType(BusinessType businessType);
}
