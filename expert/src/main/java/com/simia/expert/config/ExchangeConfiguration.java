package com.simia.expert.config;

import com.simia.share.common.exception.handler.RestTemplateErrorHandler;
import com.simia.share.common.exchange.interceptor.RestTemplateAuthorizationInterceptor;
import com.simia.share.common.exchange.properties.ExchangeProperties;
import com.simia.share.common.exchange.service.account.AccountStatisticExchangeService;
import com.simia.share.common.exchange.service.account.UserExchangeService;
import com.simia.share.common.exchange.service.account.UserPhoneExchangeService;
import com.simia.share.common.exchange.service.account.impl.AccountStatisticExchangeServiceImpl;
import com.simia.share.common.exchange.service.account.impl.UserExchangeServiceImpl;
import com.simia.share.common.exchange.service.account.impl.UserPhoneExchangeServiceImpl;
import com.simia.share.common.exchange.service.mail.MailExchangeService;
import com.simia.share.common.exchange.service.mail.impl.MailExchangeServiceImpl;
import com.simia.share.common.exchange.service.permission.GroupUserExchangeService;
import com.simia.share.common.exchange.service.permission.impl.GroupUserExchangeServiceImpl;
import com.simia.share.common.security.properties.JwtSecurityProperties;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
@ComponentScan(basePackageClasses = ExchangeProperties.class)
public class ExchangeConfiguration {

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
    public UserExchangeService userExchangeService(RestTemplate restTemplateLoadBalanced, ExchangeProperties exchangeProperties) {
        return new UserExchangeServiceImpl(restTemplateLoadBalanced, exchangeProperties);
    }

    @Bean
    public GroupUserExchangeService groupUserExchangeService(RestTemplate restTemplateLoadBalanced, ExchangeProperties exchangeProperties) {
        return new GroupUserExchangeServiceImpl(restTemplateLoadBalanced, exchangeProperties);
    }

    @Bean
    public UserPhoneExchangeService userPhoneExchangeService(RestTemplate restTemplateLoadBalanced, ExchangeProperties exchangeProperties) {
        return new UserPhoneExchangeServiceImpl(restTemplateLoadBalanced, exchangeProperties);
    }

    @Bean
    public MailExchangeService mailExchangeService(RestTemplate restTemplateLoadBalanced, ExchangeProperties exchangeProperties) {
        return new MailExchangeServiceImpl(restTemplateLoadBalanced, exchangeProperties);
    }

    @Bean
    public AccountStatisticExchangeService accountStatisticExchangeService(RestTemplate restTemplateLoadBalanced, ExchangeProperties exchangeProperties) {
        return new AccountStatisticExchangeServiceImpl(restTemplateLoadBalanced, exchangeProperties);
    }
}
