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

    /**
     * Defines a {@link ModelMapper} bean for object mapping between DTOs and entities.
     *
     * @return a new instance of {@link ModelMapper}
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
