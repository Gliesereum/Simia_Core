package com.simia.account.service.user;

import com.simia.account.model.entity.UserEntity;
import com.simia.share.common.model.dto.account.user.PublicUserDto;
import com.simia.share.common.model.dto.account.user.UserDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface UserService extends AuditableService<UserDto, UserEntity> {

    void banById(UUID id);

    void unBanById(UUID id);

    UserDto updateMe(UserDto dto);

    void setKycApproved(UUID objectId);

    UserDto getByPhone(String phone);

    void updateAsync(UserDto user);

    UserDto create(UserDto user, String referralCode);

    PublicUserDto createOrGetPublicUser(PublicUserDto user);
}
