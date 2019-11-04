package com.simia.notification.bot;

import com.simia.share.common.model.dto.karma.record.RecordNotificationDto;

public interface NotificationTelegramBotService {
	
	void recordCreateNotification(RecordNotificationDto data);
}
