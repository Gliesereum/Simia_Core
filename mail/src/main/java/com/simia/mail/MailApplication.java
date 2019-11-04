package com.simia.mail;

import com.simia.share.common.security.properties.JwtSecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication(scanBasePackages = {
		"com.simia.mail",
		"com.simia.share.common.exception.handler",
		"com.simia.share.common.exception.controller.error",
		"com.simia.share.common.security.jwt"})
@EnableEurekaClient
@EnableScheduling
@EnableAsync
@EnableConfigurationProperties(JwtSecurityProperties.class)
public class MailApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(MailApplication.class, args);
    }
}

