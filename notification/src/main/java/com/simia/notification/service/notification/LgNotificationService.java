package com.simia.notification.service.notification;

import com.simia.share.common.model.dto.lendinggallery.offer.InvestorOfferDto;
import com.simia.share.common.model.dto.notification.notification.NotificationDto;

public interface LgNotificationService {
	
	void processCreateOfferNotification(NotificationDto<InvestorOfferDto> createOfferNotification);
}
