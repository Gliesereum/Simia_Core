package com.simia.notification.service.telegram;

import com.simia.notification.model.entity.telegram.TelegramChatEntity;
import com.simia.share.common.model.dto.notification.telegram.TelegramChatDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;


public interface TelegramChatService extends DefaultService<TelegramChatDto, TelegramChatEntity> {
	
	TelegramChatDto create(Long chatId, UUID userId);
	
	boolean existByChatId(Long chatId);
	
	List<TelegramChatDto> getByUserIds(List<UUID> userIds);
}
