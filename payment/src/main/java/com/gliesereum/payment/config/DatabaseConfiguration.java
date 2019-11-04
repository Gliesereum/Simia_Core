package com.gliesereum.payment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author vitalij
 */
@Configuration
@EnableJpaRepositories("com.gliesereum.payment.model.repository.jpa")
public class DatabaseConfiguration {
}
