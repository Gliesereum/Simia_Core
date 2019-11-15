package com.simia.expert.service.followers.impl;

import com.simia.expert.model.entity.followers.FollowerEntity;
import com.simia.expert.model.repository.jpa.followers.FollowerRepository;
import com.simia.expert.service.followers.FollowerService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.CustomException;
import com.simia.share.common.model.dto.expert.followers.FollowerDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import com.simia.share.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.ExpertExceptionMessage.CERTIFICATE_NOT_FOUND;
import static com.simia.share.common.exception.messages.ExpertExceptionMessage.FOLLOWER_NOT_FOUND;


@Slf4j
@Service
public class FollowerServiceImpl extends AuditableServiceImpl<FollowerDto, FollowerEntity> implements FollowerService {

    private static final Class<FollowerDto> DTO_CLASS = FollowerDto.class;
    private static final Class<FollowerEntity> ENTITY_CLASS = FollowerEntity.class;

    private final FollowerRepository followerRepository;

    @Autowired
    public FollowerServiceImpl(FollowerRepository followerRepository, DefaultConverter defaultConverter) {
        super(followerRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.followerRepository = followerRepository;
    }

    @Override
    public List<FollowerDto> getAllByExpertId(UUID id) {
        //todo check expert is exist
        List<FollowerEntity> entities = followerRepository.getAllByExpertIdAndObjectState(id, ObjectState.ACTIVE);
        return converter.convert(entities, dtoClass);
    }

  

    private UUID getUserId() {
        SecurityUtil.checkUserByBanStatus();
        return SecurityUtil.getUserId();
    }

}
