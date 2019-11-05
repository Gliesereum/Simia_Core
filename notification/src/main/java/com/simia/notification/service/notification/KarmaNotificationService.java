package com.simia.notification.service.notification;

import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.expert.business.AbstractBusinessDto;
import com.simia.share.common.model.dto.expert.chat.ChatMessageDto;
import com.simia.share.common.model.dto.expert.record.BaseRecordDto;
import com.simia.share.common.model.dto.notification.notification.NotificationDto;
import com.simia.share.common.model.dto.notification.notification.SendNotificationDto;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface KarmaNotificationService {

    void processRecordNotification(NotificationDto<BaseRecordDto> recordNotification);

    void processBusinessNotification(NotificationDto<AbstractBusinessDto> businessNotification);

    void processChatMessageNotification(NotificationDto<ChatMessageDto> chatMessageNotification);

    <T extends DefaultDto> void processNotification(NotificationDto<T> notification);

    void sendNotificationToUser(UUID userId, String title, String body);

    void sendBusinessNotification(SendNotificationDto sendNotification);
    
    void sendSystemNotification(SendNotificationDto sendNotification);
}
