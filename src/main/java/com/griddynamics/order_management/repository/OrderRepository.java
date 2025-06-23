package com.griddynamics.order_management.repository;

import com.griddynamics.order_management.model.Customer;
import com.griddynamics.order_management.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on {@link Order} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard database operations and includes
 * a custom method to retrieve all orders placed by a specific customer.
 * </p>
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Retrieves all orders associated with the given customer.
     *
     * @param customer the customer whose orders are to be retrieved
     * @return list of orders placed by the specified customer
     */
    List<Order> findByCustomer(Customer customer);
}
