package com.griddynamics.order_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a product that can be ordered in the order management system.
 * <p>
 * Each product has a name, description, price, and available stock quantity.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    /**
     * Unique identifier for the product.
     * Auto-generated primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the product.
     * This field is required.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Optional description providing details about the product.
     */
    private String description;

    /**
     * Price of the product.
     * This field is required and represents the current selling price.
     */
    @Column(nullable = false)
    private double price;

    /**
     * Number of units available in stock.
     * This field is required and represents current inventory.
     */
    @Column(nullable = false)
    private int stockQuantity;
}
