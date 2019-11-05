package com.simia.expert.service.business.descriptions.impl;

import com.simia.expert.model.entity.business.descriptions.WorkingSpaceDescriptionEntity;
import com.simia.expert.model.repository.jpa.business.descriptions.WorkingSpaceDescriptionRepository;
import com.simia.expert.service.business.descriptions.WorkingSpaceDescriptionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.business.descriptions.WorkingSpaceDescriptionDto;
import com.simia.share.common.service.descrption.impl.DefaultDescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
public class WorkingSpaceDescriptionServiceImpl extends DefaultDescriptionServiceImpl<WorkingSpaceDescriptionDto, WorkingSpaceDescriptionEntity> implements WorkingSpaceDescriptionService {

    private static final Class<WorkingSpaceDescriptionDto> DTO_CLASS = WorkingSpaceDescriptionDto.class;
    private static final Class<WorkingSpaceDescriptionEntity> ENTITY_CLASS = WorkingSpaceDescriptionEntity.class;

    private final WorkingSpaceDescriptionRepository workingSpaceDescriptionRepository;

    @Autowired
    public WorkingSpaceDescriptionServiceImpl(WorkingSpaceDescriptionRepository workingSpaceDescriptionRepository, DefaultConverter defaultConverter) {
        super(workingSpaceDescriptionRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.workingSpaceDescriptionRepository = workingSpaceDescriptionRepository;
    }
}
