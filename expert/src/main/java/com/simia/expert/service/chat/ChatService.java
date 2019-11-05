package com.simia.expert.service.chat;

import com.simia.expert.model.entity.chat.ChatEntity;
import com.simia.share.common.model.dto.expert.chat.ChatDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface ChatService extends DefaultService<ChatDto, ChatEntity> {

    List<ChatDto> getAllByUserId(UUID userId);

    ChatDto getByUserIdAndBusinessId(UUID userId, UUID businessId);

    List<ChatDto> getAllByBusinessId(UUID businessId);
}
