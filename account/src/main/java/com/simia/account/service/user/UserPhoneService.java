package com.simia.account.service.user;

import com.simia.account.model.entity.UserPhoneEntity;
import com.simia.share.common.model.dto.account.user.UserPhoneDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author vitalij
 */
public interface UserPhoneService extends DefaultService<UserPhoneDto, UserPhoneEntity> {

    void deleteByUserId(UUID id);

    UserPhoneDto getByUserId(UUID id);

    String getPhoneByUserId(UUID id);

    UserPhoneDto getByPhone(String value);

    void sendCode(String phone, Boolean devMode);

    UserPhoneDto update(String phone, String code);

    UserPhoneDto create(String phone, String code);

    boolean checkPhoneByExist(String phone);

    List<UserPhoneDto> getByUserIds(List<UUID> ids);

    Map<UUID, UserPhoneDto> getMapByUserIds(List<UUID> ids);

    Map<UUID, String> getPhoneByUserIds(List<UUID> ids);
}
