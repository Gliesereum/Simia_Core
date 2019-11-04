package com.simia.share.common.exchange.service.permission;

import com.simia.share.common.model.dto.permission.application.ApplicationDto;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface ApplicationExchangeService {

    ApplicationDto check(UUID applicationId);
}
