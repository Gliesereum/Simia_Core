package com.simia.socket.facade;

import com.simia.socket.model.subscribe.SubscribeInfo;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface SocketSubscribeFacade {

    void validateSubscribe(SubscribeInfo subscribeInfo);
}
