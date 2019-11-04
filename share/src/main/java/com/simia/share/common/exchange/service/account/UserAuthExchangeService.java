package com.simia.share.common.exchange.service.account;

import com.simia.share.common.model.dto.account.auth.AuthDto;

public interface UserAuthExchangeService {
	
	AuthDto auth(String phone, String code);
}
