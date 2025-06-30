package com.griddynamics.order_management.controller;

import com.griddynamics.order_management.dto.OrderDTO;
import com.griddynamics.order_management.dto.OrderStatusUpdateDTO;
import com.griddynamics.order_management.exception.CustomerNotFoundException;
import com.griddynamics.order_management.exception.InsufficientStockException;
import com.griddynamics.order_management.exception.OrderNotFoundException;
import com.griddynamics.order_management.exception.ProductNotFoundException;
import com.griddynamics.order_management.model.Order;
import com.griddynamics.order_management.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing order-related operations.
 * <p>
 * Exposes endpoints to place new orders, retrieve existing ones, cancel orders,
 * update order statuses, and fetch orders by customer.
 * </p>
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    /**
     * Service for handling order-related business logic.
     */
    private final OrderService orderService;

    /**
     * Places a new order.
     *
     * @param orderDTO DTO containing the order details
     * @return the placed {@link Order} and HTTP 201 (Created)
     * @throws CustomerNotFoundException if the specified customer does not exist
     * @throws ProductNotFoundException if any product in the order is not found
     * @throws InsufficientStockException if there is not enough stock for any product
     */
    @PostMapping
    public ResponseEntity<Order> placeOrder(@Valid @RequestBody OrderDTO orderDTO)
            throws CustomerNotFoundException, ProductNotFoundException, InsufficientStockException {
        Order order = orderService.placeOrder(orderDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order to retrieve
     * @return the corresponding {@link Order} and HTTP 200 (OK)
     * @throws OrderNotFoundException if the order does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) throws OrderNotFoundException {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    /**
     * Cancels an order by setting its status to CANCELLED.
     *
     * @param id the ID of the order to cancel
     * @return the updated {@link Order} with status CANCELLED and HTTP 200 (OK)
     * @throws OrderNotFoundException if the order does not exist
     */
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long id) throws OrderNotFoundException {
        Order order = orderService.cancelOrder(id);
        return ResponseEntity.ok(order);
    }

    /**
     * Updates the status of an existing order (e.g., PLACED → SHIPPED).
     *
     * @param id the ID of the order to update
     * @param statusUpdateDTO DTO containing the new status
     * @return the updated {@link Order} and HTTP 200 (OK)
     * @throws OrderNotFoundException if the order does not exist
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @Valid @RequestBody OrderStatusUpdateDTO statusUpdateDTO) throws OrderNotFoundException {
        Order order = orderService.updateOrderStatus(id, statusUpdateDTO);
        return ResponseEntity.ok(order);
    }

    /**
     * Retrieves all orders placed by a specific customer.
     *
     * @param customerId the ID of the customer
     * @return list of {@link Order} entities for the customer and HTTP 200 (OK)
     * @throws CustomerNotFoundException if the customer does not exist
     */
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId)
            throws CustomerNotFoundException {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    /**
     * Retrieves a paginated list of orders in the system.
     *
     * @param pageable the pagination information (page, size, sort)
     * @return page of {@link Order} entities and HTTP 200 (OK)
     */
    @GetMapping("/paged")
    public ResponseEntity<Page<Order>> getAllOrdersPaged(Pageable pageable) {
        Page<Order> orders = orderService.getAllOrders(pageable);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
