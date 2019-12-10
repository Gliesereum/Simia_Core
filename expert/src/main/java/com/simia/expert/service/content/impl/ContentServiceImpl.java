package com.simia.expert.service.content.impl;

import com.simia.expert.model.entity.content.ContentEntity;
import com.simia.expert.service.category.CategoryService;
import com.simia.expert.service.content.ContentService;
import com.simia.expert.service.expert.ExpertService;
import com.simia.expert.service.media.MediaService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.CustomException;
import com.simia.share.common.model.dto.expert.content.ContentDto;
import com.simia.share.common.model.dto.expert.content.ContentFullDto;
import com.simia.share.common.model.dto.expert.expert.ExpertDto;
import com.simia.share.common.model.dto.expert.media.MediaDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import com.simia.share.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private ExpertService expertService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public ContentDto create(ContentDto dto) {
        ExpertDto expert = getExpertByUserId(SecurityUtil.getUserId());
        dto.setAuthorId(expert.getId());
        checkModel(dto);
        dto = super.create(dto);
        createOrUpdateMedia(dto);
        return dto;
    }

    @Override
    @Transactional
    public ContentDto update(ContentDto dto) {
        ExpertDto expert = getExpertByUserId(SecurityUtil.getUserId());
        if (!dto.getAuthorId().equals(expert.getId())) {
            throw new CustomException(NOT_PERMISSION_TO_CONTENT);
        }
        checkModel(dto);
        createOrUpdateMedia(dto);
        return super.update(dto);
    }

    @Override
    public List<ContentDto> getAllByExpertId(UUID id) {
        ExpertDto expert = expertService.getById(id);
        if (expert == null) {
            throw new CustomException(EXPERT_NOT_FOUND);
        }
        return getContentByExpertId(id);
    }

    @Override
    public List<ContentDto> getAllByUserId(UUID userId) {
        ExpertDto expert = getExpertByUserId(userId);
        return getAllByExpertId(expert.getId());
    }

    @Override
    public ContentFullDto getFullById(UUID id) {
        Optional<ContentEntity> entity = repository.findById(id);
        return converter.convert(entity, ContentFullDto.class);
    }

    @Override
    public List<ContentFullDto> getFullByIds(List<UUID> ids) {
        List<ContentEntity> entities = repository.findAllById(ids);
        return converter.convert(entities, ContentFullDto.class);
    }

    @Override
    public List<ContentDto> getAllByCategoryId(UUID id) {
        List<ContentEntity> entities = contentRepository.getAllByCategoryIdInAndObjectState(categoryService.getIdsChildren(id), ObjectState.ACTIVE);
        return converter.convert(entities, dtoClass);
    }

    private List<ContentDto> getContentByExpertId(UUID id) {
        List<ContentEntity> entities = contentRepository.getAllByAuthorId(id);
        return converter.convert(entities, dtoClass);
    }

    private void checkModel(ContentDto dto) {
        if (dto != null) {
            switch (dto.getContentType()) {
                case STREAM: {
                    if (dto.getStartDate() == null ||
                            dto.getStartDate().isBefore(LocalDateTime.now())) {
                        throw new CustomException(INCORRECT_START_DATE);
                    }
                    break;
                }
                case CONTENT: {
                    if (dto.getMedia() == null) {
                        throw new CustomException(MEDIA_IS_NULL);
                    }
                    break;
                }
            }
        }
    }

    private ExpertDto getExpertByUserId(UUID userId) {
        ExpertDto result = expertService.getByUserId(userId);
        if (result == null) {
            throw new CustomException(EXPERT_NOT_FOUND);
        }
        return result;
    }

    private void createOrUpdateMedia(ContentDto dto) {
        if (dto != null && dto.getId() != null) {
            MediaDto media = dto.getMedia();
            if (media != null && StringUtils.isNotBlank(media.getUrl())) {
                media.setObjectId(dto.getId());
                if (media.getId() != null) {
                    media = mediaService.update(media);
                } else {
                    media = mediaService.create(media);
                }
                dto.setMedia(media);
            }
        }
    }
}
