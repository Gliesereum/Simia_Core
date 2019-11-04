package com.simia.proxy.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simia.share.common.security.application.filter.ApplicationFilter;
import com.simia.share.common.security.bearer.filter.BearerAuthenticationFilter;
import com.simia.share.common.security.handler.ExceptionHandlerFilter;
import com.simia.share.common.security.jwt.factory.JwtTokenFactory;
import com.simia.share.common.security.jwt.factory.impl.JwtTokenFactoryImpl;
import com.simia.share.common.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * @author yvlasiuk
 * @version 1.0
 */
@Configuration
@Import({BearerAuthenticationFilter.class, ApplicationFilter.class})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private BearerAuthenticationFilter bearerAuthenticationFilter;

    @Autowired
    private CorsConfigurationSource defaultCorsConfigurationSource;

    @Autowired
    private ApplicationFilter applicationFilter;

    @Bean
    public JwtTokenFactory jwtTokenFactory(SecurityProperties securityProperties, ObjectMapper objectMapper) {
        return new JwtTokenFactoryImpl(securityProperties, objectMapper);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .anonymous().disable()
                .csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and().authorizeRequests().anyRequest().permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .addFilterBefore(bearerAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(applicationFilter, BearerAuthenticationFilter.class)
                .addFilterBefore(new ExceptionHandlerFilter(), ApplicationFilter.class)
                .cors().configurationSource(defaultCorsConfigurationSource);
    }
}
