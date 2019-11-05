package com.simia.expert.service.filter;

import com.simia.expert.model.entity.filter.FilterAttributeEntity;
import com.simia.share.common.model.dto.expert.filter.FilterAttributeDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface FilterAttributeService extends DefaultService<FilterAttributeDto, FilterAttributeEntity> {

    List<FilterAttributeDto> getByFilterId(UUID filterId);

    void checkFilterAttributeExist(UUID id);

    List<UUID> checkFilterAttributeExistAndGetAllIdsByFilterId(UUID filterAttributeId);
}
