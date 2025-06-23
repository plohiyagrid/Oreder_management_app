package com.griddynamics.order_management.exception;

/**
 * Exception thrown when a product does not have sufficient stock
 * to fulfill a requested order.
 * <p>
 * Typically used in the order placement process to prevent overselling.
 * </p>
 *
 * @see com.griddynamics.order_management.service.OrderService
 * @see com.griddynamics.order_management.model.Product
 */
public class InsufficientStockException extends RuntimeException {

    /**
     * Constructs a new InsufficientStockException with the specified detail message.
     *
     * @param message the detail message explaining the stock shortage
     */
    public InsufficientStockException(String message) {
        super(message);
    }
}
