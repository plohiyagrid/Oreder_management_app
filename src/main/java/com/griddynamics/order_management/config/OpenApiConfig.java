package com.griddynamics.order_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI documentation using Swagger.
 * <p>
 * Provides metadata for the Order Management API, such as title, description, and version.
 * This configuration enables automatic generation of API docs accessible via Swagger UI.
 * </p>
 */
@Configuration
public class OpenApiConfig {

    /**
     * Defines the {@link OpenAPI} bean with custom API information.
     *
     * @return configured {@link OpenAPI} instance for Swagger documentation
     */
    @Bean
    public OpenAPI orderManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Management API")
                        .description("API for managing customers, products, and orders")
                        .version("v1.0"));
    }
}
