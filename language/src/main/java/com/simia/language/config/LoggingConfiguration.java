package com.simia.language.config;

import com.simia.share.common.config.logging.LoggingDefaultConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * /**
 *
 * @author vitalij
 */
@Configuration
@ComponentScan(basePackageClasses = LoggingDefaultConfiguration.class)
public class LoggingConfiguration {

}
