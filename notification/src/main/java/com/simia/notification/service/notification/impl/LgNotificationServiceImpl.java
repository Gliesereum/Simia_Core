package com.simia.notification.service.notification.impl;

import com.simia.notification.service.device.UserDeviceService;
import com.simia.notification.service.firebase.FirebaseService;
import com.simia.notification.service.notification.LgNotificationService;
import com.simia.share.common.model.dto.lendinggallery.offer.InvestorOfferDto;
import com.simia.share.common.model.dto.notification.device.UserDeviceDto;
import com.simia.share.common.model.dto.notification.enumerated.SubscribeDestination;
import com.simia.share.common.model.dto.notification.notification.NotificationDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LgNotificationServiceImpl implements LgNotificationService {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private FirebaseService firebaseService;
	
	@Autowired
	private UserDeviceService userDeviceService;
	
	@Override
	public void processCreateOfferNotification(NotificationDto<InvestorOfferDto> createOfferNotification) {
		if (createOfferNotification != null) {
			SubscribeDestination subscribeDestination = createOfferNotification.getSubscribeDestination();
			InvestorOfferDto offer = createOfferNotification.getData();
			List<UUID> userIds = createOfferNotification.getUserIds();
			if (CollectionUtils.isNotEmpty(userIds)) {
				List<UserDeviceDto> devices = userDeviceService.getByUserIdsAndSubscribeExist(userIds, subscribeDestination);
				if (CollectionUtils.isNotEmpty(devices)) {
					List<String> tokens = devices.stream().map(UserDeviceDto::getFirebaseRegistrationToken).collect(Collectors.toList());
					Map<String, String> data = new HashMap<>();
					data.put("objectId", offer.getId().toString());
					data.put("offerId", offer.getId().toString());
					data.put("artBondId", offer.getArtBondId().toString());
					firebaseService.sendNotificationToDevices(tokens, getTitle(subscribeDestination), getBody(subscribeDestination), data);
				}
			}
		}
	}
	
	private String getTitle(SubscribeDestination subscribeDestination) {
		return messageSource.getMessage(subscribeDestination.toString() + '.' + "title", new Object[]{}, Locale.getDefault());
	}
	
	private String getBody(SubscribeDestination subscribeDestination) {
		return messageSource.getMessage(subscribeDestination.toString() + '.' + "body", new Object[]{}, Locale.getDefault());
	}
}
