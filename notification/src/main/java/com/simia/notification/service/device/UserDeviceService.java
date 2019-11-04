package com.simia.notification.service.device;

import com.simia.notification.model.entity.device.UserDeviceEntity;
import com.simia.share.common.model.dto.notification.device.UserDeviceDto;
import com.simia.share.common.model.dto.notification.device.UserDeviceRegistrationDto;
import com.simia.share.common.model.dto.notification.enumerated.SubscribeDestination;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface UserDeviceService extends DefaultService<UserDeviceDto, UserDeviceEntity> {

    UserDeviceDto registerDevice(UserDeviceRegistrationDto userDeviceRegistration);

    List<UserDeviceDto> getByUserId(UUID userId);

    List<UserDeviceDto> getByUserIds(List<UUID> userIds);

    List<UserDeviceDto> getByUserIdsAndSubscribeExist(List<UUID> userIds, SubscribeDestination subscribeDestination);

    UserDeviceDto getByRegistrationToken(String registrationToken);

    void removeDevice(String registrationToken);
}
