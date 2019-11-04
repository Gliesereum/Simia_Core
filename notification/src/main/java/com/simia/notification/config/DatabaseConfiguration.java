package com.simia.notification.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author vitalij
 * @version 1.0
 */
@Configuration
@EnableJpaRepositories("com.simia.notification.model.repository.jpa")
@EnableJpaAuditing
public class DatabaseConfiguration {

}
