package com.simia.account.facade.user.impl;

import com.simia.account.facade.user.UserFacade;
import com.simia.account.service.user.UserEmailService;
import com.simia.account.service.user.UserPhoneService;
import com.simia.account.service.user.UserService;
import com.simia.share.common.model.dto.account.user.*;
import org.apache.commons.collections4.CollectionUtils;
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
public class UserFacadeImpl implements UserFacade {
    
    @Autowired
    private UserPhoneService userPhoneService;

    @Autowired
    private UserEmailService userEmailService;

    @Autowired
    private UserService userService;

    @Override
    public List<DetailedUserDto> getDetailedByIds(List<UUID> ids) {
        List<DetailedUserDto> result = null;
        if (CollectionUtils.isNotEmpty(ids)) {
            List<UserDto> users = userService.getByIds(ids);
            if (CollectionUtils.isNotEmpty(users)) {
                List<UUID> userIds = users.stream().map(UserDto::getId).collect(Collectors.toList());
                Map<UUID, UserPhoneDto> phoneMap = userPhoneService.getMapByUserIds(userIds);
                Map<UUID, UserEmailDto> emailMap = userEmailService.getMapByUserIds(userIds);
                result = users.stream().map(i -> {
                    DetailedUserDto detailedUser = new DetailedUserDto();
                    detailedUser.setId(i.getId());
                    detailedUser.setUser(i);
                    detailedUser.setPhone(phoneMap.getOrDefault(i.getId(), new UserPhoneDto()).getPhone());
                    detailedUser.setEmail(emailMap.getOrDefault(i.getId(), new UserEmailDto()).getEmail());
                    return detailedUser;
                }).collect(Collectors.toList());
            }
        }
        return result;
    }

    @Override
    public List<PublicUserDto> getPublicUserByIds(List<UUID> ids) {
        List<PublicUserDto> result = null;
        if (CollectionUtils.isNotEmpty(ids)) {
            List<UserDto> users = userService.getByIds(ids);
            if (CollectionUtils.isNotEmpty(users)) {
                List<UUID> userIds = users.stream().map(UserDto::getId).collect(Collectors.toList());
                Map<UUID, UserPhoneDto> phoneMap = userPhoneService.getMapByUserIds(userIds);
                Map<UUID, UserEmailDto> emailMap = userEmailService.getMapByUserIds(userIds);
                result = users.stream().map(i -> {
                    PublicUserDto publicUser = new PublicUserDto();
                    publicUser.setId(i.getId());
                    publicUser.setFirstName(i.getFirstName());
                    publicUser.setLastName(i.getLastName());
                    publicUser.setAvatarUrl(i.getAvatarUrl());
                    publicUser.setPhone(phoneMap.getOrDefault(i.getId(), new UserPhoneDto()).getPhone());
                    publicUser.setEmail(emailMap.getOrDefault(i.getId(), new UserEmailDto()).getEmail());
                    return publicUser;
                }).collect(Collectors.toList());
            }
        }
        return result;
    }
}
