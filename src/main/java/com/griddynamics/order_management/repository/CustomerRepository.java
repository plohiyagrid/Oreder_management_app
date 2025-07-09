package com.griddynamics.order_management.repository;

import com.griddynamics.order_management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository interface for performing CRUD operations on {@link Customer} entities.
 * <p>
 * Extends {@link JpaRepository} to provide built-in methods for interacting with
 * the database, including pagination and sorting support.
 * </p>
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
