package com.griddynamics.order_management.service;

import com.griddynamics.order_management.dto.CustomerDTO;
import com.griddynamics.order_management.exception.CustomerNotFoundException;
import com.griddynamics.order_management.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

import java.util.List;

/**
 * Service interface for handling business logic related to {@link Customer} operations.
 * <p>
 * Provides methods for registering a new customer, retrieving a customer by ID,
 * and fetching all registered customers.
 * </p>
 */
public interface CustomerService {

    /**
     * Registers a new customer in the system using the provided customer data.
     *
     * @param customerDTO data transfer object containing customer information
     * @return the newly created {@link Customer} entity
     */
    Customer registerCustomer(CustomerDTO customerDTO);

    /**
     * Retrieves a customer by their unique identifier.
     *
     * @param id the ID of the customer to retrieve
     * @return the corresponding {@link Customer} entity
     * @throws CustomerNotFoundException if no customer is found with the given ID
     */
    Customer getCustomerById(Long id) throws CustomerNotFoundException;

    /**
     * Retrieves all customers currently registered in the system.
     *
     * @return list of all {@link Customer} entities
     */
    List<Customer> getAllCustomers();

    /**
     * Paginated, filtered, and sorted search for customers.
     */
    Page<Customer> searchCustomers(String name, String email, LocalDate createdAfter, Pageable pageable);
}
