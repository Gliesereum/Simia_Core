package com.simia.proxy.config;

import com.simia.share.common.config.rabbitmq.RabbitMQDefaultConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yvlasiuk
 * @version 1.0
 */
@Configuration
@ComponentScan(
        value = {
		        "com.simia.share.common.logging.service",
		        "com.simia.share.common.logging.appender"},
        basePackageClasses = RabbitMQDefaultConfiguration.class)
public class LoggingConfiguration {
}
