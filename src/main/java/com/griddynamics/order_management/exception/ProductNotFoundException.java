package com.griddynamics.order_management.exception;

/**
 * Exception thrown when a product is not found in the system.
 * <p>
 * Typically used in service or controller layers when a requested product
 * does not exist in the database.
 * </p>
 *
 * @see com.griddynamics.order_management.service.ProductService
 * @see com.griddynamics.order_management.controller.ProductController
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Constructs a new ProductNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the exception
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
