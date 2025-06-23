package com.griddynamics.order_management.dto;

import com.griddynamics.order_management.model.Order;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for updating the status of an existing order.
 * <p>
 * Used in API requests to modify the {@link Order.OrderStatus} field.
 * Includes validation to ensure the status is not null.
 * </p>
 */
@Data
public class OrderStatusUpdateDTO {

    /**
     * New status to be assigned to the order.
     * This field is required and must not be null.
     */
    @NotNull(message = "Status is required")
    private Order.OrderStatus status;
}
