package com.simia.expert.service.record.impl;

import com.simia.expert.model.entity.record.RecordMessageEntity;
import com.simia.expert.model.repository.jpa.record.RecordMessageRepository;
import com.simia.expert.service.record.RecordMessageDescriptionService;
import com.simia.expert.service.record.RecordMessageService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.record.RecordMessageDescriptionDto;
import com.simia.share.common.model.dto.expert.record.RecordMessageDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class RecordMessageServiceImpl extends DefaultServiceImpl<RecordMessageDto, RecordMessageEntity> implements RecordMessageService {

    private static final Class<RecordMessageDto> DTO_CLASS = RecordMessageDto.class;
    private static final Class<RecordMessageEntity> ENTITY_CLASS = RecordMessageEntity.class;

    private final RecordMessageRepository repository;

    @Autowired
    private RecordMessageDescriptionService recordMessageDescriptionService;

    @Autowired
    public RecordMessageServiceImpl(RecordMessageRepository repository, DefaultConverter defaultConverter) {
        super(repository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.repository = repository;
    }

    @Override
    @Transactional
    public RecordMessageDto create(RecordMessageDto dto) {
        RecordMessageDto result = null;
        if (dto != null) {
            result = super.create(dto);
            List<RecordMessageDescriptionDto> descriptions = recordMessageDescriptionService.create(dto.getDescriptions(), result.getId());
            result.setDescriptions(descriptions);
        }
        return result;
    }

    @Override
    @Transactional
    public RecordMessageDto update(RecordMessageDto dto) {
        RecordMessageDto result = null;
        if (dto != null) {
            result = super.update(dto);
            List<RecordMessageDescriptionDto> descriptions = recordMessageDescriptionService.update(dto.getDescriptions(), result.getId());
            result.setDescriptions(descriptions);
        }
        return result;
    }

}
