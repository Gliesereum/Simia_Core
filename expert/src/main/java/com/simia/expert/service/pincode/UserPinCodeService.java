package com.simia.expert.service.pincode;

import com.simia.expert.model.entity.pincode.UserPinCodeEntity;
import com.simia.share.common.model.dto.expert.pincode.UserPinCodeDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface UserPinCodeService extends DefaultService<UserPinCodeDto, UserPinCodeEntity> {

    UserPinCodeDto getByUserId(UUID userId);

    UserPinCodeDto save(UserPinCodeDto userPinCode);

    void remindMe();

    void deleteByUserId(UUID userId);
}
