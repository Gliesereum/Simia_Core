package com.simia.expert.service.chat;

import com.simia.expert.model.entity.chat.ChatSupportEntity;
import com.simia.share.common.model.dto.expert.chat.ChatSupportDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface ChatSupportService extends DefaultService<ChatSupportDto, ChatSupportEntity> {

    List<ChatSupportDto> getByBusinessId(UUID businessId);

    boolean existChatSupport(UUID userId, UUID businessId);
}    
