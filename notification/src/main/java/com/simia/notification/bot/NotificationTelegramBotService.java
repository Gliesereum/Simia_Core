package com.simia.notification.bot;

import com.simia.share.common.model.dto.expert.record.RecordNotificationDto;

public interface NotificationTelegramBotService {
	
	void recordCreateNotification(RecordNotificationDto data);
}
