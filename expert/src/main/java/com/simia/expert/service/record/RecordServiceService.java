package com.simia.expert.service.record;

import com.simia.expert.model.entity.record.RecordServiceEntity;
import com.simia.share.common.model.dto.expert.record.RecordServiceDto;
import com.simia.share.common.model.dto.expert.service.ServicePriceDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 * @since 12/7/18
 */
public interface RecordServiceService extends DefaultService<RecordServiceDto, RecordServiceEntity> {

    Map<UUID, List<UUID>> getServicePriceIds(List<UUID> recordIds);

    Map<UUID, List<ServicePriceDto>> getServicePriceMap(List<UUID> recordIds);
}
