package com.simia.account.service.auth;

import com.simia.account.model.domain.TokenStoreDomain;
import com.simia.share.common.model.dto.account.auth.AuthDto;
import com.simia.share.common.model.dto.account.auth.SignInDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface AuthService {

    AuthDto signIn(SignInDto signInDto);

    //TODO: merge signin with signup
    //AuthDto signUp(Map<String, String> params);

    AuthDto check(String accessToken);

    AuthDto refresh(String refreshToken);

    AuthDto createAuthModel(TokenStoreDomain token, UUID userId);

    List<AuthDto> createAuthModel(Map<UUID, List<TokenStoreDomain>> userTokenMap);
}
