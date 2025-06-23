package com.griddynamics.order_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for updating the stock quantity of a product.
 * <p>
 * Used in API requests to modify the inventory count of an existing product.
 * Includes validation to ensure a valid, non-negative stock quantity is provided.
 * </p>
 */
@Data
public class StockUpdateDTO {

    /**
     * New stock quantity for the product.
     * This field is required and must be zero or a positive integer.
     */
    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity must be a positive number")
    private Integer stockQuantity;
}
