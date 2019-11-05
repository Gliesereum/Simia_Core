package com.simia.expert.service.service;

import com.simia.expert.model.entity.service.ServiceEntity;
import com.simia.share.common.model.dto.expert.service.ServiceDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface ServiceService extends DefaultService<ServiceDto, ServiceEntity> {

    List<ServiceDto> getAllByBusinessCategoryId(UUID businessCategoryId);
}
