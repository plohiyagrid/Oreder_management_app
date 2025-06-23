package com.griddynamics.order_management.exception;

/**
 * Exception thrown when an order is not found in the system.
 * <p>
 * Typically used in service or controller layers when a requested order
 * does not exist in the database.
 * </p>
 *
 * @see com.griddynamics.order_management.service.OrderService
 * @see com.griddynamics.order_management.controller.OrderController
 */
public class OrderNotFoundException extends RuntimeException {

    /**
     * Constructs a new OrderNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the exception
     */
    public OrderNotFoundException(String message) {
        super(message);
    }
}
