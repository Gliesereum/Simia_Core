package com.simia.share.common.config.migration;

import com.simia.share.common.config.executor.ThreadPoolTaskExecutorDefaultConfiguration;
import com.simia.share.common.exception.handler.RestTemplateErrorHandler;
import com.simia.share.common.exchange.interceptor.RestTemplateAuthorizationInterceptor;
import com.simia.share.common.exchange.properties.ExchangeProperties;
import com.simia.share.common.exchange.service.permission.EndpointExchangeService;
import com.simia.share.common.exchange.service.permission.impl.EndpointExchangeServiceImpl;
import com.simia.share.common.migration.EndpointsListener;
import com.simia.share.common.security.properties.JwtSecurityProperties;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
public class EndpointListenerDefaultConfiguration {

    @Bean
    @LoadBalanced
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(RestTemplateAuthorizationInterceptor restTemplateAuthorizationInterceptor) {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> existed = restTemplate.getInterceptors();
        List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = new ArrayList<>();
        clientHttpRequestInterceptors.add(restTemplateAuthorizationInterceptor);
        if (CollectionUtils.isNotEmpty(existed)) {
            clientHttpRequestInterceptors.addAll(existed);
        }
        restTemplate.setInterceptors(clientHttpRequestInterceptors);
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        return restTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public RestTemplateAuthorizationInterceptor restTemplateAuthorizationInterceptor(JwtSecurityProperties jwtSecurityProperties) {
        return new RestTemplateAuthorizationInterceptor(jwtSecurityProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public EndpointExchangeService endpointExchangeService(ExchangeProperties exchangeProperties, RestTemplate restTemplate) {
        return new EndpointExchangeServiceImpl(restTemplate, exchangeProperties);
    }
}
