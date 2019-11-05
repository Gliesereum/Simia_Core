package com.simia.expert.facade.notification.impl;

import com.simia.expert.facade.notification.BusinessNotificationFacade;
import com.simia.share.common.model.dto.expert.business.AbstractBusinessDto;
import com.simia.share.common.model.dto.notification.enumerated.SubscribeDestination;
import com.simia.share.common.model.dto.notification.notification.NotificationDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
public class BusinessNotificationFacadeImpl implements BusinessNotificationFacade {

    @Value("${notification.business.queueName}")
    private String notificationBusinessQueue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Async
    @Override
    public void newBusinessNotification(AbstractBusinessDto business) {
        if (business != null) {
            NotificationDto<AbstractBusinessDto> notification = new NotificationDto<>();
            notification.setData(business);
            notification.setSubscribeDestination(SubscribeDestination.KARMA_USER_NEW_BUSINESS);
            notification.setObjectId(business.getId());
            rabbitTemplate.convertAndSend(notificationBusinessQueue, notification);
        }
    }
}
