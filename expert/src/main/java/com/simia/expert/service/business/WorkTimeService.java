package com.simia.expert.service.business;

import com.simia.expert.model.entity.business.WorkTimeEntity;
import com.simia.share.common.model.dto.expert.business.WorkTimeDto;
import com.simia.share.common.model.dto.expert.business.WorkerDto;
import com.simia.share.common.model.dto.expert.enumerated.WorkTimeType;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface WorkTimeService extends DefaultService<WorkTimeDto, WorkTimeEntity> {

    List<WorkTimeDto> getByObjectId(UUID businessId);

    void delete(UUID id, UUID businessCategoryId, WorkTimeType type);

    void deleteByObjectId(UUID id);

    void checkWorkTimesByBusyTime(List<WorkTimeDto> list, WorkerDto worker);
}
