package com.simia.proxy.service.impl;

import com.simia.share.common.exchange.service.permission.impl.ApplicationExchangeServiceImpl;
import com.simia.share.common.model.dto.permission.application.ApplicationDto;
import com.simia.share.common.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static com.simia.proxy.config.CacheConfiguration.APPLICATION_INFO_CACHE;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Slf4j
@Service
public class CacheApplicationExchangeServiceImpl extends ApplicationExchangeServiceImpl {

    public CacheApplicationExchangeServiceImpl(RestTemplate restTemplate, SecurityProperties securityProperties) {
        super(restTemplate, securityProperties);
    }

    @Override
    @Cacheable(value = APPLICATION_INFO_CACHE, key = "#applicationId", unless = "#result == null")
    public ApplicationDto check(UUID applicationId) {
        return super.check(applicationId);
    }
}
