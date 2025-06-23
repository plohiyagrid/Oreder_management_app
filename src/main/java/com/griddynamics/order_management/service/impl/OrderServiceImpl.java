package com.griddynamics.order_management.service.impl;

import com.griddynamics.order_management.dto.*;
import com.griddynamics.order_management.exception.CustomerNotFoundException;
import com.griddynamics.order_management.exception.InsufficientStockException;
import com.griddynamics.order_management.exception.OrderNotFoundException;
import com.griddynamics.order_management.exception.ProductNotFoundException;
import com.griddynamics.order_management.model.Customer;
import com.griddynamics.order_management.model.Order;
import com.griddynamics.order_management.model.OrderItem;
import com.griddynamics.order_management.model.Product;
import com.griddynamics.order_management.repository.OrderRepository;
import com.griddynamics.order_management.service.CustomerService;
import com.griddynamics.order_management.service.OrderService;
import com.griddynamics.order_management.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link OrderService} interface, providing business logic
 * for placing, updating, retrieving, and canceling customer orders.
 * <p>
 * This service handles stock validation, entity relationships, and transactional integrity.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    /**
     * Places a new order after validating customer and product data, and stock availability.
     *
     * @param orderDTO the data transfer object containing order details
     * @return the created {@link Order} entity
     * @throws CustomerNotFoundException if the customer does not exist
     * @throws ProductNotFoundException if a product in the order is not found
     * @throws InsufficientStockException if any product lacks sufficient stock
     */
    @Override
    @Transactional
    public Order placeOrder(OrderDTO orderDTO) throws CustomerNotFoundException, ProductNotFoundException, InsufficientStockException {
        Customer customer = customerService.getCustomerById(orderDTO.getCustomerId());

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.OrderStatus.PLACED);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            Product product = productService.getProductById(itemDTO.getProductId());

            if (product.getStockQuantity() < itemDTO.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product: " + product.getName() +
                        ". Available: " + product.getStockQuantity() +
                        ", Requested: " + itemDTO.getQuantity());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPriceAtPurchase(product.getPrice());

            orderItems.add(orderItem);

            product.setStockQuantity(product.getStockQuantity() - itemDTO.getQuantity());
            productService.createProduct(modelMapper.map(product, ProductDTO.class));
        }

        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }

    /**
     * Retrieves an order by its unique identifier.
     *
     * @param id the order ID
     * @return the corresponding {@link Order} entity
     * @throws OrderNotFoundException if the order does not exist
     */
    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(Long id) throws OrderNotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }

    /**
     * Cancels an order by setting its status to CANCELLED and restocking the associated products.
     *
     * @param id the ID of the order to cancel
     * @return the updated {@link Order} entity
     * @throws OrderNotFoundException if the order does not exist
     */
    @Override
    @Transactional
    public Order cancelOrder(Long id) throws OrderNotFoundException {
        Order order = getOrderById(id);
        if (order.getStatus() == Order.OrderStatus.CANCELLED) {
            return order;
        }

        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
            try {
                productService.createProduct(modelMapper.map(product, ProductDTO.class));
            } catch (ProductNotFoundException e) {
                throw new RuntimeException("Product not found during cancellation: " + product.getId(), e);
            }
        }

        order.setStatus(Order.OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    /**
     * Updates the status of an existing order.
     *
     * @param id the ID of the order to update
     * @param statusUpdateDTO DTO containing the new status
     * @return the updated {@link Order} entity
     * @throws OrderNotFoundException if the order does not exist
     */
    @Override
    @Transactional
    public Order updateOrderStatus(Long id, OrderStatusUpdateDTO statusUpdateDTO) throws OrderNotFoundException {
        Order order = getOrderById(id);
        order.setStatus(statusUpdateDTO.getStatus());
        return orderRepository.save(order);
    }

    /**
     * Retrieves all orders placed by a specific customer.
     *
     * @param customerId the ID of the customer
     * @return list of {@link Order} entities belonging to the customer
     * @throws CustomerNotFoundException if the customer does not exist
     */
    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrdersByCustomerId(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerService.getCustomerById(customerId);
        return orderRepository.findByCustomer(customer);
    }
}
