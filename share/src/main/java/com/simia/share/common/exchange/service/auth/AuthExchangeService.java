package com.simia.share.common.exchange.service.auth;

import com.simia.share.common.model.dto.account.auth.AuthDto;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface AuthExchangeService {

    AuthDto checkAccessToken(String accessToken);
}
