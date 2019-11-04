package com.simia.account;

import com.simia.share.common.security.properties.JwtSecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
		"com.simia.account",
		"com.simia.share.common.exception.handler",
		"com.simia.share.common.exception.controller.error",
		"com.simia.share.common.security.jwt"})
@EnableAsync
@EnableScheduling
@EnableEurekaClient
@EnableConfigurationProperties(JwtSecurityProperties.class)
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
