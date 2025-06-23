package com.griddynamics.order_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer in the order management system.
 * <p>
 * Each customer can place multiple orders. The customer entity contains
 * identifying information such as name, email, and phone number.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    /**
     * Unique identifier for the customer.
     * Auto-generated primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Full name of the customer.
     * This field is required.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Unique email address of the customer.
     * This field is required and must be unique.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Contact phone number of the customer.
     * This field is optional.
     */
    private String phoneNumber;

    /**
     * List of orders placed by the customer.
     * This is a one-to-many relationship; one customer can have many orders.
     * Orders are lazily loaded and ignored during JSON serialization.
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();
}
