package com.simia.notification.service.telegram.impl;

import com.simia.notification.model.entity.telegram.TelegramChatEntity;
import com.simia.notification.model.repository.jpa.telegram.TelegramChatRepository;
import com.simia.notification.service.telegram.TelegramChatService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.notification.telegram.TelegramChatDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class TelegramChatServiceImpl extends DefaultServiceImpl<TelegramChatDto, TelegramChatEntity> implements TelegramChatService {
	
	private static final Class<TelegramChatDto> DTO_CLASS = TelegramChatDto.class;
	private static final Class<TelegramChatEntity> ENTITY_CLASS = TelegramChatEntity.class;
	
	private final TelegramChatRepository telegramChatRepository;
	
	@Autowired
	public TelegramChatServiceImpl(TelegramChatRepository telegramChatRepository, DefaultConverter defaultConverter) {
		super(telegramChatRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
		this.telegramChatRepository = telegramChatRepository;
	}
	
	@Override
	public TelegramChatDto create(Long chatId, UUID userId) {
		TelegramChatDto result = null;
		if (ObjectUtils.allNotNull(chatId, userId)) {
			if (!telegramChatRepository.existsByUserIdAndChatId(userId, chatId)) {
				result = new TelegramChatDto();
				result.setChatId(chatId);
				result.setUserId(userId);
				result = super.create(result);
			}
		}
		return result;
	}
	
	@Override
	public boolean existByChatId(Long chatId) {
		return telegramChatRepository.existsByChatId(chatId);
	}
	
	@Override
	public List<TelegramChatDto> getByUserIds(List<UUID> userIds) {
		List<TelegramChatDto> result = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(userIds)) {
			List<TelegramChatEntity> byUserIdIn = telegramChatRepository.findByUserIdIn(userIds);
			if (CollectionUtils.isNotEmpty(byUserIdIn)) {
				result = converter.convert(byUserIdIn, dtoClass);
			}
		}
		return result;
	}
}
