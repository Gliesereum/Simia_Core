package com.simia.expert.service.chat;

import com.simia.expert.model.entity.chat.ChatMessageEntity;
import com.simia.share.common.model.dto.expert.chat.ChatMessageDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface ChatMessageService extends DefaultService<ChatMessageDto, ChatMessageEntity> {
    
    List<ChatMessageDto> getAllByChatId(UUID id);

    ChatMessageDto createByBusinessId(UUID id, String message);

    ChatMessageDto createByChatId(UUID id, String message);
}    
