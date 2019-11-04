package com.simia.notification.service.subscribe;

import com.simia.notification.model.entity.subscribe.UserSubscribeEntity;
import com.simia.share.common.model.dto.notification.device.UserDeviceRegistrationDto;
import com.simia.share.common.model.dto.notification.enumerated.SubscribeDestination;
import com.simia.share.common.model.dto.notification.subscribe.UserSubscribeDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface UserSubscribeService extends DefaultService<UserSubscribeDto, UserSubscribeEntity> {

    List<UserSubscribeDto> subscribe(List<UserSubscribeDto> subscribes, UUID userId, String registrationToken);

    List<UserSubscribeDto> getSubscribes(SubscribeDestination subscribeDestination, UUID objectId);

    List<UserSubscribeDto> getByUserDeviceId(UUID userDeviceId);

    List<UserSubscribeDto> getAllByUserDeviceIdAndSubscribeDestination(List<UUID> userDeviceId, SubscribeDestination subscribeDestination);

    List<UserSubscribeDto> getByRegistrationToken(String registrationToken);

    void unsubscribeAll(UUID deviceId, String registrationToke);

    void unsubscribe(UUID deviceId, String registrationToken, SubscribeDestination subscribeDestination, UUID objectId);

    List<UserSubscribeDto> addSubscribes(UserDeviceRegistrationDto userDeviceRegistration, Boolean overrideExistedDestination);
}
