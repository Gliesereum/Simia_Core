package com.simia.expert.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author vitalij
 * @version 1.0
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories({"com.simia.expert.model.repository.jpa",
		"com.simia.share.common.repository.refreshable"})
public class DatabaseConfiguration {
}
