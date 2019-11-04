package com.simia.socket.facade.impl;

import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.exchange.service.karma.KarmaExchangeService;
import com.simia.share.common.model.dto.karma.business.BaseBusinessDto;
import com.simia.share.common.util.RegexUtil;
import com.simia.share.common.util.SecurityUtil;
import com.simia.socket.facade.SocketSubscribeFacade;
import com.simia.socket.model.subscribe.SubscribeInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.CommonExceptionMessage.USER_IS_ANONYMOUS;
import static com.simia.share.common.exception.messages.SocketExceptionMessage.*;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service("karmaSocketSubscribeFacadeImp")
public class KarmaSocketSubscribeImpl implements SocketSubscribeFacade {

    private static final String USER_RECORD = "userRecord";
    private static final String BUSINESS_RECORD = "businessRecord";

    @Autowired
    private KarmaExchangeService karmaExchangeService;

    @Override
    public void validateSubscribe(SubscribeInfo subscribeInfo) {
        if (SecurityUtil.isAnonymous()) {
            throw new ClientException(USER_IS_ANONYMOUS);
        }
        switch (subscribeInfo.getSubscribeDestination()) {
            case USER_RECORD:
                validateSubscribeUserRecord(subscribeInfo);
                break;
            case BUSINESS_RECORD:
                validateSubscribeBusinessRecord(subscribeInfo);
                break;


        }
    }

    private void validateSubscribeUserRecord(SubscribeInfo subscribeInfo) {
        if (!RegexUtil.isUUID(subscribeInfo.getSubscribeProperties())) {
            throw new ClientException(INVALID_ID_TO_SUBSCRIBE);
        }
        if (!SecurityUtil.getUserId().equals(UUID.fromString(subscribeInfo.getSubscribeProperties()))) {
            throw new ClientException(USER_CAN_SUBSCRIBE_ONLY_TO_THEMSELVES);
        }
    }


    private void validateSubscribeBusinessRecord(SubscribeInfo subscribeInfo) {
        if (!RegexUtil.isUUID(subscribeInfo.getSubscribeProperties())) {
            throw new ClientException(INVALID_ID_TO_SUBSCRIBE);
        }
        List<BaseBusinessDto> business = karmaExchangeService.getBusinessForCurrentUser();
        if (CollectionUtils.isEmpty(business) || business.stream().noneMatch(i -> i.getId().equals(UUID.fromString(subscribeInfo.getSubscribeProperties())))) {
            throw new ClientException(USER_CANT_SUBSCRIBE_TO_THIS_BUSINESS);
        }
    }
}
