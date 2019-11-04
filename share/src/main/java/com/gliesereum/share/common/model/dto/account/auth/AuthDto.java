package com.gliesereum.share.common.model.dto.account.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gliesereum.share.common.model.dto.account.user.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthDto {

    private UserDto user;

    private TokenInfoDto tokenInfo;
}
