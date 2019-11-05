package com.simia.expert.facade.notification;

import com.simia.share.common.model.dto.expert.chat.ChatMessageDto;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface ChatNotificationFacade {

    void userMessageNotification(ChatMessageDto chatMessage, UUID userId);

    void businessMessageNotification(ChatMessageDto chatMessage, UUID businessId);
}
