package com.simia.notification.service.telegram;

import com.simia.notification.model.entity.telegram.TelegramChatActionEntity;
import com.simia.share.common.model.dto.notification.enumerated.TelegramAction;
import com.simia.share.common.model.dto.notification.telegram.TelegramChatActionDto;
import com.simia.share.common.service.DefaultService;


public interface TelegramChatActionService extends DefaultService<TelegramChatActionDto, TelegramChatActionEntity> {
	
	TelegramChatActionDto getByChatId(Long chatId);
	
	TelegramChatActionDto create(Long chatId, TelegramAction action);
	
	TelegramChatActionDto create(Long chatId, TelegramAction action, String value);
	
	void deleteByChatId(Long chatId);
}
