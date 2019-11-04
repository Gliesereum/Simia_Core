package com.simia.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author yvlasiuk
 * @version 1.0
 */
@Configuration
@EnableJpaRepositories("com.simia.account.model.repository.jpa")
@EnableJpaAuditing
public class DatabaseConfiguration {
}
