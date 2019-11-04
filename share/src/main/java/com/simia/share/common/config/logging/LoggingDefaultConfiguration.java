package com.simia.share.common.config.logging;

import com.simia.share.common.config.rabbitmq.RabbitMQDefaultConfiguration;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Configuration
@ComponentScan(
        value = "com.simia.share.common.logging",
        basePackageClasses = RabbitMQDefaultConfiguration.class)
public class LoggingDefaultConfiguration {

    @Bean("queueLogstashSystem")
    public Queue queueLogstashSystem(@Value("${spring.rabbitmq.queue-logstash-system}") String queueName) {
        return new Queue(queueName);
    }

    @Bean("queueLogstashRequest")
    public Queue queueLogstashRequest(@Value("${spring.rabbitmq.queue-logstash-request}") String queueName) {
        return new Queue(queueName);
    }
}
