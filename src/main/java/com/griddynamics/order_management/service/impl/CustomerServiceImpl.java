package com.griddynamics.order_management.service.impl;

import com.griddynamics.order_management.dto.CustomerDTO;
import com.griddynamics.order_management.exception.CustomerNotFoundException;
import com.griddynamics.order_management.model.Customer;
import com.griddynamics.order_management.repository.CustomerRepository;
import com.griddynamics.order_management.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of {@link CustomerService} that provides business logic for
 * registering and retrieving customers from the database.
 * <p>
 * Uses {@link ModelMapper} for converting DTOs to entity objects and
 * {@link CustomerRepository} for data access operations.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    /**
     * Repository for performing CRUD operations on {@link Customer} entities.
     */
    private final CustomerRepository customerRepository;

    /**
     * Mapper for converting between DTOs and entities.
     */
    private final ModelMapper modelMapper;

    /**
     * Registers a new customer in the system.
     *
     * @param customerDTO data transfer object containing customer information
     * @return the saved {@link Customer} entity
     */
    @Override
    @Transactional
    public Customer registerCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        return customerRepository.save(customer);
    }

    /**
     * Retrieves a customer by their unique ID.
     *
     * @param id the ID of the customer to retrieve
     * @return the corresponding {@link Customer} entity
     * @throws CustomerNotFoundException if no customer is found with the given ID
     */
    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id) throws CustomerNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    /**
     * Retrieves all customers currently registered in the system.
     *
     * @return list of all {@link Customer} entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> searchCustomers(String name, String email, LocalDate createdAfter, Pageable pageable) {
        Specification<Customer> spec = (root, query, cb) -> {
            List<javax.persistence.criteria.Predicate> predicates = new java.util.ArrayList<>();
            if (name != null) {
                predicates.add(cb.like(cb.lower(root.get("name")), name.toLowerCase() + "%"));
            }
            if (email != null) {
                predicates.add(cb.equal(root.get("email"), email));
            }
            if (createdAfter != null) {
                predicates.add(cb.greaterThan(root.get("createdAt"), createdAfter));
            }
            return cb.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        };
        return customerRepository.findAll(spec, pageable);
    }
}
