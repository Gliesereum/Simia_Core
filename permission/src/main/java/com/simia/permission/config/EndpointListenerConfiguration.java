package com.simia.permission.config;

import com.simia.permission.service.endpoint.EndpointService;
import com.simia.share.common.config.executor.ThreadPoolTaskExecutorDefaultConfiguration;
import com.simia.share.common.exchange.properties.ExchangeProperties;
import com.simia.share.common.exchange.service.permission.EndpointExchangeService;
import com.simia.share.common.migration.EndpointsListener;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yvlasiuk
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(ExchangeProperties.class)
@ComponentScan(basePackageClasses = {
        EndpointsListener.class,
        ThreadPoolTaskExecutorDefaultConfiguration.class
})
public class EndpointListenerConfiguration {

    @Bean
    public EndpointExchangeService endpointExchangeService(EndpointService endpointService) {
        return endpointService::createPack;
    }
}
