package com.simia.socket.config;

import com.simia.share.common.config.executor.ThreadPoolTaskExecutorDefaultConfiguration;
import com.simia.share.common.migration.EndpointsListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yurii Vlasiuk
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackageClasses = {
        EndpointsListener.class,
        ThreadPoolTaskExecutorDefaultConfiguration.class
})
public class EndpointListenerConfiguration {
}
