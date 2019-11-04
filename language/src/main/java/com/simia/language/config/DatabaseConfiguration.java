package com.simia.language.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author vitalij
 */
@Configuration
@EnableJpaRepositories({
		"com.simia.language.model.repository.jpa",
		"com.simia.share.common.repository.refreshable"})
public class DatabaseConfiguration {
}
