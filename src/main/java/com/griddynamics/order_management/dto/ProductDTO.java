package com.griddynamics.order_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for transferring product information between layers.
 * <p>
 * Used in API requests and responses to encapsulate product details without exposing
 * the full entity. Includes validation annotations for input integrity.
 * </p>
 */
@Data
public class ProductDTO {

    /**
     * Unique identifier of the product.
     * May be null when creating a new product.
     */
    private Long id;

    /**
     * Name of the product.
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * Optional description of the product.
     */
    private String description;

    /**
     * Price of the product.
     * Must be a non-negative value.
     * This field is required.
     */
    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be a positive number")
    private Double price;

    /**
     * Number of items available in stock.
     * Must be a non-negative value.
     * This field is required.
     */
    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity must be a positive number")
    private Integer stockQuantity;
}
