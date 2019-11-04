package com.simia.share.common.security.jwt.factory;

import com.simia.share.common.security.model.UserAuthentication;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface JwtTokenFactory {

    String getJwtToken(UserAuthentication userAuthentication);

    UserAuthentication parseJwtToken(String jwtToken);
}
