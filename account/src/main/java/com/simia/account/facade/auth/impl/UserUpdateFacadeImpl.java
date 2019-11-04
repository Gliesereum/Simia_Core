package com.simia.account.facade.auth.impl;

import com.simia.account.facade.auth.UserUpdateFacade;
import com.simia.account.facade.notification.SystemNotificationFacade;
import com.simia.account.model.domain.TokenStoreDomain;
import com.simia.account.service.auth.AuthService;
import com.simia.account.service.token.TokenService;
import com.simia.share.common.model.dto.account.auth.AuthDto;
import com.simia.share.common.model.dto.account.user.UserDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
public class UserUpdateFacadeImpl implements UserUpdateFacade {

    @Lazy
    @Autowired
    private TokenService tokenService;

    @Lazy
    @Autowired
    private AuthService authService;

    @Lazy
    @Autowired
    private SystemNotificationFacade systemNotificationFacade;

    @Override
    @Async
    public void tokenInfoUpdateEvent(List<UUID> userIds) {
        if (CollectionUtils.isNotEmpty(userIds)) {
            Map<UUID, List<TokenStoreDomain>> tokens = tokenService.getByUserIds(userIds);
            if (MapUtils.isNotEmpty(tokens)) {
                List<AuthDto> authModel = authService.createAuthModel(tokens);
                systemNotificationFacade.sendUpdateAuthInfoNotification(authModel);
            }

        }
    }

    @Override
    @Async
    public void updateClientInfo(UserDto user) {
        if(user != null){
            systemNotificationFacade.updateClientInfo(user);
        }
    }
}
