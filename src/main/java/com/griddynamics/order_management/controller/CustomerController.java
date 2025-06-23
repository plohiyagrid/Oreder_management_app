package com.griddynamics.order_management.controller;

import com.griddynamics.order_management.dto.CustomerDTO;
import com.griddynamics.order_management.exception.CustomerNotFoundException;
import com.griddynamics.order_management.model.Customer;
import com.griddynamics.order_management.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing customer-related operations.
 * <p>
 * Provides endpoints to register a new customer, retrieve a customer by ID,
 * and list all customers.
 * </p>
 */
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    /**
     * Service for handling customer business logic.
     */
    private final CustomerService customerService;

    /**
     * Registers a new customer using the provided customer data.
     *
     * @param customerDTO the DTO containing customer details
     * @return the created {@link Customer} along with HTTP 201 (Created) status
     */
    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.registerCustomer(customerDTO);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    /**
     * Retrieves a customer by their unique ID.
     *
     * @param id the ID of the customer to retrieve
     * @return the corresponding {@link Customer} along with HTTP 200 (OK) status
     * @throws CustomerNotFoundException if no customer is found with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /**
     * Retrieves a list of all registered customers.
     *
     * @return list of {@link Customer} entities along with HTTP 200 (OK) status
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
