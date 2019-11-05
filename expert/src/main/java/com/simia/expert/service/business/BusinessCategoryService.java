package com.simia.expert.service.business;

import com.simia.expert.model.entity.business.BusinessCategoryEntity;
import com.simia.share.common.model.dto.expert.business.BusinessCategoryDto;
import com.simia.share.common.model.dto.expert.enumerated.BusinessType;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface BusinessCategoryService extends DefaultService<BusinessCategoryDto, BusinessCategoryEntity> {

    List<BusinessCategoryDto> getByBusinessType(BusinessType businessType);

    BusinessCategoryDto getByCode(String code);

    boolean existByCode(String code);

    boolean existsByCodeIdNot(String code, UUID id);

    BusinessType checkAndGetType(UUID businessCategoryId);

    List<BusinessCategoryDto> getAllSortByIndex();
}
