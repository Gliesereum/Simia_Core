package com.simia.expert.service.chat.impl;

import com.simia.expert.facade.business.BusinessPermissionFacade;
import com.simia.expert.model.common.BusinessPermission;
import com.simia.expert.model.entity.chat.ChatEntity;
import com.simia.expert.model.repository.jpa.chat.ChatRepository;
import com.simia.expert.service.chat.ChatService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.expert.chat.ChatDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.KarmaExceptionMessage.BUSINESS_ID_EMPTY;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class ChatServiceImpl extends DefaultServiceImpl<ChatDto, ChatEntity> implements ChatService {

    private static final Class<ChatDto> DTO_CLASS = ChatDto.class;
    private static final Class<ChatEntity> ENTITY_CLASS = ChatEntity.class;

    private final ChatRepository chatRepository;

    @Autowired
    private BusinessPermissionFacade businessPermissionFacade;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, DefaultConverter defaultConverter) {
        super(chatRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.chatRepository = chatRepository;
    }

    @Override
    public List<ChatDto> getAllByUserId(UUID userId) {
        List<ChatEntity> entities = chatRepository.getAllByUserId(userId);
        return converter.convert(entities, dtoClass);
    }

    @Override
    public ChatDto getByUserIdAndBusinessId(UUID userId, UUID businessId) {
        ChatEntity entity = chatRepository.getByUserIdAndBusinessId(userId, businessId);
        return converter.convert(entity, dtoClass);
    }

    @Override
    public List<ChatDto> getAllByBusinessId(UUID businessId) {
        if (businessId == null) {
            throw new ClientException(BUSINESS_ID_EMPTY);
        }
        businessPermissionFacade.checkPermissionByBusiness(businessId, BusinessPermission.WORK_WITH_CHAT);
        List<ChatEntity> entities = chatRepository.getAllByBusinessId(businessId);
        return converter.convert(entities, dtoClass);
    }
}
