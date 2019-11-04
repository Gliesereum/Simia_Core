package com.simia.notification.service.firebase.impl;

import com.simia.notification.service.device.UserDeviceService;
import com.simia.notification.service.firebase.FirebaseMessageCode;
import com.simia.notification.service.firebase.FirebaseService;
import com.simia.share.common.model.dto.notification.enumerated.SubscribeDestination;
import com.simia.share.common.util.NotificationUtil;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
@Slf4j
public class FirebaseServiceImpl implements FirebaseService {
	
	@Autowired
	private FirebaseMessaging firebaseMessagingKarma;
	
	@Autowired
	private UserDeviceService userDeviceService;
	
	@Override
	public TopicManagementResponse subscribeToTopic(String registrationToken, String topicDestination) {
		TopicManagementResponse response = null;
		try {
			response = firebaseMessagingKarma.subscribeToTopic(Arrays.asList(registrationToken), topicDestination);
		} catch (FirebaseMessagingException e) {
			log.warn("Error while subscribe", e);
		}
		return response;
	}
	
	@Override
	public TopicManagementResponse subscribeToTopic(String registrationToken, String subscribeDestination, UUID subscribeId) {
		TopicManagementResponse response = null;
		if (StringUtils.isNotBlank(registrationToken) && StringUtils.isNotBlank(subscribeDestination)) {
			String topicDestination = NotificationUtil.routingKey(subscribeDestination, subscribeId);
			response = subscribeToTopic(registrationToken, topicDestination);
		}
		return response;
	}
	
	@Override
	public TopicManagementResponse unsubscribeFromTopic(String registrationToken, String subscribeDestination, UUID subscribeId) {
		TopicManagementResponse response = null;
		try {
			response = firebaseMessagingKarma.unsubscribeFromTopic(Arrays.asList(registrationToken), NotificationUtil.routingKey(subscribeDestination, subscribeId));
		} catch (FirebaseMessagingException e) {
			log.warn("Error while unsubscribe", e);
		}
		return response;
	}
    
    @Override
    public void sendNotificationToTopic(String topic, String title, String body, UUID objectId, SubscribeDestination subscribeDestination, Map<String, String> data) {
        Message.Builder builder = Message.builder();
        if (objectId != null) {
            builder = builder.putData("objectId", objectId.toString());
        }
        if (MapUtils.isNotEmpty(data)) {
            builder.putAllData(data);
        }
        builder = builder.putData("title", title)
                .putData("body", body)
                .putData("event", subscribeDestination.toString())
                .setApnsConfig(ApnsConfig.builder()
                        .setAps(Aps.builder()
                                .setAlert(ApsAlert.builder()
                                        .setTitle(title)
                                        .setBody(body)
                                        .build())
                                .setBadge(1)
                                .build())
                        .build())
                .setTopic(topic);
        Message message = builder.build();
    
        try {
            firebaseMessagingKarma.send(message);
        } catch (FirebaseMessagingException e) {
            log.warn("Error while send message", e);
        }
    }
    
    @Override
	public void sendNotificationToTopic(String topic, String title, String body, UUID objectId, SubscribeDestination subscribeDestination) {
		sendNotificationToTopic(topic, title, body, objectId, subscribeDestination, null);
	}
	
	@Override
	public void sendNotificationToDevice(String registrationToken, String title, String body, Map<String, String> data) {
		Message message = buildMessage(registrationToken, title, body, data);
		try {
			firebaseMessagingKarma.send(message);
		} catch (FirebaseMessagingException e) {
			log.warn("Error while send message for device", e);
			if (e.getErrorCode().equals(FirebaseMessageCode.REGISTRATION_TOKEN_NOT_FOUND)) {
				log.warn("Try remove device by registration token");
				userDeviceService.removeDevice(registrationToken);
			}
		}
	}
	
	@Override
	public void sendNotificationToDevices(List<String> registrationTokens, String title, String body, Map<String, String> data) {
		List<Message> messages = registrationTokens.stream()
				.map(registrationToken -> buildMessage(registrationToken, title, body, data))
				.collect(Collectors.toList());
		try {
			firebaseMessagingKarma.sendAll(messages);
		} catch (FirebaseMessagingException e) {
			log.warn("Error while send batch messages for ", e);
			
		}
	}
	
	private Message buildMessage(String registrationToken, String title, String body, Map<String, String> data) {
		data = MapUtils.emptyIfNull(data);
		return Message.builder()
				.putData("title", title)
				.putData("body", body)
				.putAllData(data)
				.setNotification(new Notification(title, body))
				.setApnsConfig(ApnsConfig.builder()
						.setAps(Aps.builder()
								.setAlert(ApsAlert.builder()
										.setTitle(title)
										.setBody(body)
										.build())
								.setBadge(1)
								.build())
						.build())
				.setToken(registrationToken)
				.build();
	}
}
