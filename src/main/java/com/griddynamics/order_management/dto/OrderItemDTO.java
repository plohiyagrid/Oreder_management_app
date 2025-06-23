package com.griddynamics.order_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing an item in an order.
 * <p>
 * Contains the product ID and quantity for a single item within an order.
 * Includes validation to ensure required fields and valid values.
 * </p>
 */
@Data
public class OrderItemDTO {

    /**
     * ID of the product being ordered.
     * This field is required and must not be null.
     */
    @NotNull(message = "Product ID is required")
    private Long productId;

    /**
     * Quantity of the product being ordered.
     * This field is required and must be at least 1.
     */
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
