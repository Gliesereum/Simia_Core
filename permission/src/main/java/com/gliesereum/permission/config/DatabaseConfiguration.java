package com.gliesereum.permission.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author yvlasiuk
 * @version 1.0
 */
@Configuration
@EnableJpaRepositories("com.gliesereum.permission.model.repository")
public class DatabaseConfiguration {
}
