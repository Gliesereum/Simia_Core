package com.simia.mail.service.telegram;

import com.simia.mail.model.entity.TelegramBotEntity;
import com.simia.share.common.model.dto.mail.TelegramBotDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;

public interface TelegramBotService extends DefaultService<TelegramBotDto, TelegramBotEntity> {

    TelegramBotDto getByChatId(Long chatId);

    List<TelegramBotDto> getByActive(Boolean active);

}
