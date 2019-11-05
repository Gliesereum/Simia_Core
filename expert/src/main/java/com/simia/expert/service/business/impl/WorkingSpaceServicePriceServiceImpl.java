package com.simia.expert.service.business.impl;

import com.simia.expert.model.entity.business.WorkingSpaceServicePriceEntity;
import com.simia.expert.model.repository.jpa.business.WorkingSpaceServicePriceRepository;
import com.simia.expert.service.business.WorkingSpaceServicePriceService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.business.WorkingSpaceServicePriceDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class WorkingSpaceServicePriceServiceImpl extends DefaultServiceImpl<WorkingSpaceServicePriceDto, WorkingSpaceServicePriceEntity> implements WorkingSpaceServicePriceService {

    private final WorkingSpaceServicePriceRepository repository;

    private static final Class<WorkingSpaceServicePriceDto> DTO_CLASS = WorkingSpaceServicePriceDto.class;
    private static final Class<WorkingSpaceServicePriceEntity> ENTITY_CLASS = WorkingSpaceServicePriceEntity.class;

    public WorkingSpaceServicePriceServiceImpl(WorkingSpaceServicePriceRepository repository, DefaultConverter defaultConverter) {
        super(repository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.repository = repository;
    }

    @Override
    @Transactional
    public WorkingSpaceServicePriceDto create(WorkingSpaceServicePriceDto dto) {
        return super.create(dto);
    }

    @Override
    @Transactional
    public WorkingSpaceServicePriceDto update(WorkingSpaceServicePriceDto dto) {
        return super.update(dto);
    }

    @Override
    public WorkingSpaceServicePriceDto getById(UUID id) {
        return super.getById(id);
    }

    @Transactional
    public void delete(UUID id) {
        super.delete(id);
    }
}
