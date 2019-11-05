package com.simia.expert.service.service;

import com.simia.expert.model.entity.service.ServiceClassEntity;
import com.simia.share.common.model.dto.expert.service.ServiceClassDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 * @since 12/5/18
 */
public interface ServiceClassService extends DefaultService<ServiceClassDto, ServiceClassEntity> {

    boolean existsService(UUID id);
}
