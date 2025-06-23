package com.griddynamics.order_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for transferring customer information between layers.
 * <p>
 * Used in API requests and responses to encapsulate customer data without exposing
 * the full entity. Includes validation annotations for input validation.
 * </p>
 */
@Data
public class CustomerDTO {

    /**
     * Unique identifier of the customer.
     * May be null when creating a new customer.
     */
    private Long id;

    /**
     * Full name of the customer.
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * Email address of the customer.
     * This field is required, must not be blank, and must follow a valid email format.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    /**
     * Optional contact phone number of the customer.
     */
    private String phoneNumber;
}
