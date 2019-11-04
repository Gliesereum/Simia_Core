package com.simia.file.config;

import com.simia.share.common.config.migration.EndpointListenerDefaultConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yvlasiuk
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackageClasses = EndpointListenerDefaultConfiguration.class)
public class EndpointListenerConfiguration {
}
