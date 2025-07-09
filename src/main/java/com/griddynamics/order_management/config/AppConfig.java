package com.griddynamics.order_management.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for application-wide bean definitions.
 * <p>
 * Provides commonly used beans like {@link ModelMapper} for dependency injection.
 * </p>
 */
@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
