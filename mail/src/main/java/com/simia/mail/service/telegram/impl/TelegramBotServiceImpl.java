package com.simia.mail.service.telegram.impl;

import com.simia.mail.model.entity.TelegramBotEntity;
import com.simia.mail.model.repository.jpa.TelegramBotRepository;
import com.simia.mail.service.telegram.TelegramBotService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.mail.TelegramBotDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class TelegramBotServiceImpl extends DefaultServiceImpl<TelegramBotDto, TelegramBotEntity> implements TelegramBotService {

    private static final Class<TelegramBotDto> DTO_CLASS = TelegramBotDto.class;
    private static final Class<TelegramBotEntity> ENTITY_CLASS = TelegramBotEntity.class;

    private final TelegramBotRepository telegramBotRepository;

    @Autowired
    public TelegramBotServiceImpl(TelegramBotRepository telegramBotRepository, DefaultConverter defaultConverter) {
        super(telegramBotRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.telegramBotRepository = telegramBotRepository;
    }

    @Override
    public TelegramBotDto getByChatId(Long chatId) {
        TelegramBotEntity entity = telegramBotRepository.getByChatId(chatId);
        return converter.convert(entity, dtoClass);
    }

    @Override
    public List<TelegramBotDto> getByActive(Boolean active) {
        List<TelegramBotEntity> entities = telegramBotRepository.getByActive(active);
        return converter.convert(entities, dtoClass);
    }

}
