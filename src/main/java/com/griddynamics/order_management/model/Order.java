package com.griddynamics.order_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer order in the order management system.
 * <p>
 * Each order is associated with a customer, contains a list of order items,
 * and tracks the order date and status.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    /**
     * Unique identifier for the order.
     * Auto-generated primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The customer who placed the order.
     * This is a many-to-one relationship; multiple orders can belong to a single customer.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Date and time when the order was placed.
     * This field is required.
     */
    @Column(nullable = false)
    private LocalDateTime orderDate;

    /**
     * Current status of the order.
     * Uses an enumerated type to store values as strings in the database.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    /**
     * List of items included in this order.
     * One-to-many relationship with {@link OrderItem}.
     * Items are lazily loaded and cascade on all operations.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * Enum representing the status of an order.
     */
    public enum OrderStatus {
        /** The order has been placed but not yet processed. */
        PLACED,
        /** The order has been shipped to the customer. */
        SHIPPED,
        /** The order has been cancelled. */
        CANCELLED
    }
}
