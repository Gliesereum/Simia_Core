package com.simia.expert.service.content.impl;

import com.simia.expert.model.entity.content.ContentEntity;
import com.simia.expert.model.repository.jpa.content.ContentRepository;
import com.simia.expert.service.content.ContentService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.CustomException;
import com.simia.share.common.model.dto.expert.content.ContentDto;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import com.simia.share.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.ExpertExceptionMessage.*;

@Slf4j
@Service
public class ContentServiceImpl extends AuditableServiceImpl<ContentDto, ContentEntity> implements ContentService {

    private static final Class<ContentDto> DTO_CLASS = ContentDto.class;
    private static final Class<ContentEntity> ENTITY_CLASS = ContentEntity.class;

    private final ContentRepository contentRepository;

    @Autowired
    public ContentServiceImpl(ContentRepository contentRepository, DefaultConverter defaultConverter) {
        super(contentRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.contentRepository = contentRepository;
    }

    @Override
    @Transactional
    public ContentDto create(ContentDto dto) {
        dto.setAuthorId(getUserId());//todo maybe set expertId
        checkModel(dto);
        return super.create(dto);
    }

    @Override
    @Transactional
    public ContentDto update(ContentDto dto) {
        checkPermission(dto);
        checkModel(dto);
        return super.update(dto);
    }

    

    private void checkPermission(ContentDto dto){
        if(!dto.getAuthorId().equals(getUserId())){
         throw new CustomException(NOT_PERMISSION_TO_CONTENT);
        }
    }

    private UUID getUserId() {
        SecurityUtil.checkUserByBanStatus();
        return SecurityUtil.getUserId();
    }
}
