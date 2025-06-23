package com.griddynamics.order_management.dto;

import com.griddynamics.order_management.model.Order;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object (DTO) for transferring order data between application layers.
 * <p>
 * Encapsulates key information about an order, including the customer ID, order date,
 * status, and list of associated order items.
 * </p>
 */
@Data
public class OrderDTO {

    /**
     * Unique identifier of the order.
     */
    private Long id;

    /**
     * ID of the customer who placed the order.
     */
    private Long customerId;

    /**
     * Timestamp indicating when the order was placed.
     */
    private LocalDateTime orderDate;

    /**
     * Current status of the order (e.g., PLACED, SHIPPED, CANCELLED).
     */
    private Order.OrderStatus status;

    /**
     * List of items included in the order.
     */
    private List<OrderItemDTO> orderItems;
}
