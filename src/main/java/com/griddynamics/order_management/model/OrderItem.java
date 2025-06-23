package com.griddynamics.order_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an item within an order.
 * <p>
 * Each {@code OrderItem} refers to a specific product and is part of a particular order.
 * It stores the quantity and price of the product at the time of purchase.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {

    /**
     * Unique identifier for the order item.
     * Auto-generated primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The product associated with this item.
     * This is a many-to-one relationship; multiple order items can refer to the same product.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * The order to which this item belongs.
     * This is a many-to-one relationship; an order can have multiple items.
     * This field is ignored during JSON serialization to avoid circular references.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    private Order order;

    /**
     * Quantity of the product in this order item.
     * This field is required and must be a positive integer.
     */
    @Column(nullable = false)
    private int quantity;

    /**
     * Price of the product at the time this order was placed.
     * This value is stored to retain historical pricing.
     */
    @Column(nullable = false)
    private double priceAtPurchase;
}
