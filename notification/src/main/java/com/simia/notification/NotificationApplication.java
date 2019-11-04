package com.simia.notification;

import com.simia.share.common.security.properties.JwtSecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication(
		scanBasePackages = {"com.simia.notification",
				"com.simia.share.common.exception.handler",
				"com.simia.share.common.exception.controller.error",
				"com.simia.share.common.security.jwt"},
		exclude = { ElasticsearchAutoConfiguration.class,
				ElasticsearchDataAutoConfiguration.class })
@EnableAsync
@EnableEurekaClient
@EnableConfigurationProperties(JwtSecurityProperties.class)
public class NotificationApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(NotificationApplication.class, args);
	}

}
