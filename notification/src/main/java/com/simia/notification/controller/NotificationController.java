package com.simia.notification.controller;

import com.simia.notification.service.notification.KarmaNotificationService;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.notification.notification.SendNotificationDto;
import com.simia.share.common.model.response.MapResponse;
import com.simia.share.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.simia.share.common.exception.messages.CommonExceptionMessage.USER_IS_ANONYMOUS;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private KarmaNotificationService karmaNotificationService;

    @PostMapping("/send")
    public MapResponse sendNotification(@Valid @RequestBody SendNotificationDto sendNotification) {
        if (SecurityUtil.isAnonymous()) {
            throw new ClientException(USER_IS_ANONYMOUS);
        }
        karmaNotificationService.sendBusinessNotification(sendNotification);
        return new MapResponse("true");
    }
    
    @PostMapping("/system/topic")
    public MapResponse sendSystemNotification(@Valid @RequestBody SendNotificationDto sendNotification) {
        karmaNotificationService.sendSystemNotification(sendNotification);
        return new MapResponse("true");
    }
}
