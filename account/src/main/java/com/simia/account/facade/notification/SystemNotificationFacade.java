package com.simia.account.facade.notification;

import com.simia.share.common.model.dto.account.auth.AuthDto;
import com.simia.share.common.model.dto.account.referral.ReferralCodeUserDto;
import com.simia.share.common.model.dto.account.user.UserDto;

import java.util.List;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface SystemNotificationFacade {

    void sendSignUpWithCodeNotification(ReferralCodeUserDto referralCodeUser);

    void sendUpdateAuthInfoNotification(List<AuthDto> authInfos);

    void updateClientInfo(UserDto user);
}
