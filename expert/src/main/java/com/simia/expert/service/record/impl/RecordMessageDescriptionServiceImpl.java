package com.simia.expert.service.record.impl;

import com.simia.expert.model.entity.record.RecordMessageDescriptionEntity;
import com.simia.expert.model.repository.jpa.record.RecordMessageDescriptionRepository;
import com.simia.expert.service.record.RecordMessageDescriptionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.record.RecordMessageDescriptionDto;
import com.simia.share.common.service.descrption.impl.DefaultDescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vitalij
 * @version 1.0
 */

@Service
public class RecordMessageDescriptionServiceImpl extends DefaultDescriptionServiceImpl<RecordMessageDescriptionDto, RecordMessageDescriptionEntity> implements RecordMessageDescriptionService {

    private static final Class<RecordMessageDescriptionDto> DTO_CLASS = RecordMessageDescriptionDto.class;
    private static final Class<RecordMessageDescriptionEntity> ENTITY_CLASS = RecordMessageDescriptionEntity.class;

    private final RecordMessageDescriptionRepository recordMessageDescriptionRepository;

    @Autowired
    public RecordMessageDescriptionServiceImpl(RecordMessageDescriptionRepository recordMessageDescriptionRepository, DefaultConverter defaultConverter) {
        super(recordMessageDescriptionRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.recordMessageDescriptionRepository = recordMessageDescriptionRepository;
    }
}
