package com.simia.share.common.model.dto.notification.telegram;

import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.notification.enumerated.TelegramAction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TelegramChatActionDto extends DefaultDto {
	
	private Long chatId;
	
	private TelegramAction action;
	
	private String value;
}
