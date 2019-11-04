package com.simia.file.config;

import com.simia.share.common.config.swagger.SwaggerDefaultConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Configuration
@ComponentScan(basePackageClasses = SwaggerDefaultConfiguration.class)
public class SwaggerConfiguration {
}
