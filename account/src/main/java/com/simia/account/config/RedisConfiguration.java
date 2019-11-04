package com.simia.account.config;

import com.simia.share.common.config.redis.RedisDefaultConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author yvlasiuk
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackageClasses = RedisDefaultConfiguration.class)
@EnableRedisRepositories("com.simia.account.model.repository.redis")
public class RedisConfiguration {
}
