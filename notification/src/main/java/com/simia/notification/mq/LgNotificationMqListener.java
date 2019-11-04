package com.simia.notification.mq;

import com.simia.notification.service.notification.LgNotificationService;
import com.simia.share.common.model.dto.lendinggallery.offer.InvestorOfferDto;
import com.simia.share.common.model.dto.notification.notification.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LgNotificationMqListener {
	
	@Autowired
	private LgNotificationService lgNotificationService;
	
	@RabbitListener(queuesToDeclare = @Queue(name = "${notification.lg.create-offer.queueName}", ignoreDeclarationExceptions = "true"))
	public void receiveCreateOfferNotification(NotificationDto<InvestorOfferDto> createOfferNotification) {
		try {
			lgNotificationService.processCreateOfferNotification(createOfferNotification);
		} catch (Exception e) {
			log.warn("Error while receive record notification", e);
		}
	}
}
