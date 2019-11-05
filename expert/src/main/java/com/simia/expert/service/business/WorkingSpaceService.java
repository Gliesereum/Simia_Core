package com.simia.expert.service.business;

import com.simia.expert.model.entity.business.WorkingSpaceEntity;
import com.simia.share.common.model.dto.expert.business.LiteWorkingSpaceDto;
import com.simia.share.common.model.dto.expert.business.WorkingSpaceDto;
import com.simia.share.common.model.dto.expert.business.WorkingSpaceServicePriceDto;
import com.simia.share.common.service.DefaultService;

import java.util.*;

/**
 * @author vitalij
 * @version 1.0
 */
public interface WorkingSpaceService extends DefaultService<WorkingSpaceDto, WorkingSpaceEntity> {

    List<WorkingSpaceDto> getByBusinessId(UUID businessId, boolean setUsers);

    List<LiteWorkingSpaceDto> getLiteWorkingSpaceByBusinessId(UUID id);

    List<WorkingSpaceServicePriceDto> addServicePrice(List<WorkingSpaceServicePriceDto> dtos);

    LiteWorkingSpaceDto getLiteWorkingSpaceById(UUID workingSpaceId);

    Map<UUID, LiteWorkingSpaceDto> getLiteWorkingSpaceMapByIds(Collection<UUID> collect);

    boolean isExistByIdAndBusinessId(UUID id, UUID businessId);
}
