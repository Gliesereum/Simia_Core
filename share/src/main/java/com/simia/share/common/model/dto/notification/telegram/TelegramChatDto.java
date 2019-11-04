package com.simia.share.common.model.dto.notification.telegram;

import com.simia.share.common.model.dto.DefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TelegramChatDto extends DefaultDto {
	
	private Long chatId;
	
	private UUID userId;
	
}
