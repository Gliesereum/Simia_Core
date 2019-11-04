package com.simia.mail.service.feedback.impl;

import com.simia.mail.model.entity.FeedBackUserEntity;
import com.simia.mail.model.repository.jpa.FeedBackUserRepository;
import com.simia.mail.service.feedback.FeedBackUserService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.mail.FeedBackUserDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class FeedBackUserServiceImpl extends DefaultServiceImpl<FeedBackUserDto, FeedBackUserEntity> implements FeedBackUserService {

    private static final Class<FeedBackUserDto> DTO_CLASS = FeedBackUserDto.class;
    private static final Class<FeedBackUserEntity> ENTITY_CLASS = FeedBackUserEntity.class;

    private final FeedBackUserRepository feedBackUserRepository;

    @Autowired
    public FeedBackUserServiceImpl(FeedBackUserRepository feedBackUserRepository, DefaultConverter defaultConverter) {
        super(feedBackUserRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.feedBackUserRepository = feedBackUserRepository;
    }

    @Override
    @Transactional
    public FeedBackUserDto create(FeedBackUserDto dto) {
        FeedBackUserDto result = null;
        if (dto != null && !feedBackUserRepository.existsByAppIdAndUserId(dto.getAppId(), dto.getUserId())) {
            result = super.create(dto);
        }
        return result;
    }


    @Override
    public List<FeedBackUserDto> getAllByAppId(UUID id) {
        List<FeedBackUserEntity> entities = feedBackUserRepository.getAllByAppId(id);
        return converter.convert(entities, dtoClass);
    }

    @Override
    @Transactional
    public List<FeedBackUserDto> createList(List<FeedBackUserDto> dtos) {
        if (CollectionUtils.isNotEmpty(dtos)) {
            dtos = dtos.stream().filter(f -> !feedBackUserRepository.existsByAppIdAndUserId(f.getAppId(), f.getUserId())).collect(Collectors.toList());
        }
        return super.create(dtos);
    }
}
