package com.griddynamics.order_management.repository;

import com.griddynamics.order_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on {@link Product} entities.
 * <p>
 * Extends {@link JpaRepository} to provide convenient data access methods,
 * including support for pagination and sorting.
 * </p>
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
