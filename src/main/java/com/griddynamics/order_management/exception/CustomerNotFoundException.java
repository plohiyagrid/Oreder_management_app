package com.griddynamics.order_management.exception;

/**
 * Exception thrown when a customer is not found in the system.
 * <p>
 * Typically used in service or controller layers when a requested customer
 * does not exist in the database.
 * </p>
 *
 * @see com.griddynamics.order_management.service.CustomerService
 * @see com.griddynamics.order_management.controller.CustomerController
 */
public class CustomerNotFoundException extends RuntimeException {

    /**
     * Constructs a new CustomerNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining why the exception was thrown
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
