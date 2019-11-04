package com.simia.notification.controller;

import com.simia.notification.service.notification.KarmaNotificationService;
import com.simia.share.common.model.dto.karma.record.BaseRecordDto;
import com.simia.share.common.model.dto.notification.enumerated.SubscribeDestination;
import com.simia.share.common.model.dto.notification.notification.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

//TODO: REMOVE
@RestController
public class TestContoller {

    @Autowired
    private KarmaNotificationService karmaNotificationService;

    @GetMapping("/test-record")
    public void testRecord(@RequestParam("id") UUID id) {
        NotificationDto<BaseRecordDto> notificationDto = new NotificationDto<>();
        BaseRecordDto baseRecordDto = new BaseRecordDto();
        baseRecordDto.setId(UUID.fromString("547524ed-a06d-4c99-a3cb-68e70da2f7b9"));

        notificationDto.setData(baseRecordDto);
        notificationDto.setSubscribeDestination(SubscribeDestination.KARMA_USER_RECORD);
        notificationDto.setObjectId(id);
        karmaNotificationService.processRecordNotification(notificationDto);
    }

    @GetMapping("/test-remind")
    public void testRemind(@RequestParam("id") UUID id) {
        NotificationDto<BaseRecordDto> notificationDto = new NotificationDto<>();
        BaseRecordDto baseRecordDto = new BaseRecordDto();
        baseRecordDto.setId(UUID.fromString("547524ed-a06d-4c99-a3cb-68e70da2f7b9"));

        notificationDto.setData(baseRecordDto);
        notificationDto.setSubscribeDestination(SubscribeDestination.KARMA_USER_REMIND_RECORD);
        notificationDto.setObjectId(id);
        karmaNotificationService.processRecordNotification(notificationDto);
    }

    @GetMapping("/test-user")
    public void testUser(@RequestParam("id") UUID id, @RequestParam("title") String title, @RequestParam("body") String body) {
        karmaNotificationService.sendNotificationToUser(id, title, body);
    }
}
