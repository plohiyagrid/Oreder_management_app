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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import com.griddynamics.order_management.dto.PaginatedResponse;

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
    public ResponseEntity<PaginatedResponse<Customer>> getCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdAfter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        Sort sortObj = Sort.by(
            java.util.Arrays.stream(sort)
                .map(s -> {
                    String[] parts = s.split(",");
                    return parts.length == 2 ? new Sort.Order(Sort.Direction.fromString(parts[1]), parts[0]) : new Sort.Order(Sort.Direction.ASC, s);
                })
                .toList()
        );
        Pageable pageable = PageRequest.of(page, size, sortObj);
        Page<Customer> customerPage = customerService.searchCustomers(name, email, createdAfter, pageable);
        return ResponseEntity.ok(new PaginatedResponse<>(customerPage));
    }
}
