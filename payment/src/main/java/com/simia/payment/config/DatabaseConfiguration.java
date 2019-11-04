package com.simia.payment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author vitalij
 */
@Configuration
@EnableJpaRepositories("com.simia.payment.model.repository.jpa")
public class DatabaseConfiguration {
}
