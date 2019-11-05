package com.simia.expert.service.service;

import com.simia.expert.model.entity.service.ServiceClassPriceEntity;
import com.simia.share.common.model.dto.expert.service.ServiceClassPriceDto;
import com.simia.share.common.model.dto.expert.service.ServicePriceDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface ServiceClassPriceService extends DefaultService<ServiceClassPriceDto, ServiceClassPriceEntity> {

    List<ServiceClassPriceDto> getByPriceId(UUID priceId);

    void delete(UUID priceId, UUID classId);

    ServicePriceDto createAndGetPrice(ServiceClassPriceDto dto);

    ServicePriceDto updateAndGetPrice(ServiceClassPriceDto dto);

    ServicePriceDto createAndGetPrice(UUID idPrice, List<UUID> classes);
}
