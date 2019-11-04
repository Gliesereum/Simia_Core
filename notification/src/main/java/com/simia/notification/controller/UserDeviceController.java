package com.simia.notification.controller;

import com.simia.notification.service.device.UserDeviceService;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.notification.device.UserDeviceDto;
import com.simia.share.common.model.dto.notification.device.UserDeviceRegistrationDto;
import com.simia.share.common.model.response.MapResponse;
import com.simia.share.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.simia.share.common.exception.messages.CommonExceptionMessage.USER_IS_ANONYMOUS;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@RestController
@RequestMapping("/user-device")
public class UserDeviceController {

    @Autowired
    private UserDeviceService userDeviceService;

    @PostMapping
    public UserDeviceDto addUserDevice(@Valid @RequestBody UserDeviceRegistrationDto userDeviceRegistration) {
        SecurityUtil.checkUserByBanStatus();
        userDeviceRegistration.setUserId(SecurityUtil.getUserId());
        return userDeviceService.registerDevice(userDeviceRegistration);
    }

    @GetMapping("/by-user")
    public List<UserDeviceDto> getAllByUser() {
        if (SecurityUtil.isAnonymous()) {
            throw new ClientException(USER_IS_ANONYMOUS);
        }
        return userDeviceService.getByUserId(SecurityUtil.getUserId());
    }

    @GetMapping("/by-registration-token")
    public UserDeviceDto getByRegistrationToken(String registrationToken) {
        return userDeviceService.getByRegistrationToken(registrationToken);
    }

    @DeleteMapping
    public MapResponse deleteDevice(@RequestParam("registrationToken") String registrationToken) {
        userDeviceService.removeDevice(registrationToken);
        return MapResponse.resultTrue();
    }
}
