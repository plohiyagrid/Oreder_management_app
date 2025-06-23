package com.griddynamics.order_management.service;

import com.griddynamics.order_management.dto.OrderDTO;
import com.griddynamics.order_management.dto.OrderStatusUpdateDTO;
import com.griddynamics.order_management.exception.CustomerNotFoundException;
import com.griddynamics.order_management.exception.InsufficientStockException;
import com.griddynamics.order_management.exception.OrderNotFoundException;
import com.griddynamics.order_management.exception.ProductNotFoundException;
import com.griddynamics.order_management.model.Order;

import java.util.List;

/**
 * Service interface for handling business logic related to {@link Order} operations.
 * <p>
 * Provides functionality to place, retrieve, cancel, and update orders,
 * including validation of stock and customer/product existence.
 * </p>
 */
public interface OrderService {

    /**
     * Places a new order using the provided order details.
     * Validates customer existence and product availability.
     *
     * @param orderDTO the data transfer object containing order details
     * @return the created {@link Order} entity
     * @throws CustomerNotFoundException if the customer does not exist
     * @throws ProductNotFoundException if any product in the order is not found
     * @throws InsufficientStockException if any product does not have sufficient stock
     */
    Order placeOrder(OrderDTO orderDTO) throws CustomerNotFoundException, ProductNotFoundException, InsufficientStockException;

    /**
     * Retrieves an order by its unique ID.
     *
     * @param id the ID of the order to retrieve
     * @return the corresponding {@link Order} entity
     * @throws OrderNotFoundException if no order is found with the given ID
     */
    Order getOrderById(Long id) throws OrderNotFoundException;

    /**
     * Cancels an existing order by its ID.
     *
     * @param id the ID of the order to cancel
     * @return the updated {@link Order} with status set to CANCELLED
     * @throws OrderNotFoundException if no order is found with the given ID
     */
    Order cancelOrder(Long id) throws OrderNotFoundException;

    /**
     * Updates the status of an existing order.
     *
     * @param id the ID of the order to update
     * @param statusUpdateDTO DTO containing the new order status
     * @return the updated {@link Order} with the new status
     * @throws OrderNotFoundException if no order is found with the given ID
     */
    Order updateOrderStatus(Long id, OrderStatusUpdateDTO statusUpdateDTO) throws OrderNotFoundException;

    /**
     * Retrieves all orders placed by a specific customer.
     *
     * @param customerId the ID of the customer
     * @return list of {@link Order} entities belonging to the customer
     * @throws CustomerNotFoundException if the customer does not exist
     */
    List<Order> getOrdersByCustomerId(Long customerId) throws CustomerNotFoundException;
}
