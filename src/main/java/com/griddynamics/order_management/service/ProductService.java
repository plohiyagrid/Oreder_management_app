package com.griddynamics.order_management.service;

import com.griddynamics.order_management.dto.ProductDTO;
import com.griddynamics.order_management.dto.StockUpdateDTO;
import com.griddynamics.order_management.exception.ProductNotFoundException;
import com.griddynamics.order_management.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for handling business logic related to {@link Product} operations.
 * <p>
 * Provides methods for creating products, retrieving them, and updating stock quantities.
 * </p>
 */
public interface ProductService {

    /**
     * Creates a new product in the system using the provided product data.
     *
     * @param productDTO the data transfer object containing product details
     * @return the newly created {@link Product} entity
     */
    Product createProduct(ProductDTO productDTO);

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id the ID of the product to retrieve
     * @return the corresponding {@link Product} entity
     * @throws ProductNotFoundException if no product is found with the given ID
     */
    Product getProductById(Long id) throws ProductNotFoundException;

    /**
     * Retrieves all products available in the system.
     *
     * @return list of all {@link Product} entities
     */
    List<Product> getAllProducts();

    /**
     * Retrieves a paginated list of products.
     *
     * @param pageable the pagination information
     * @return a page of {@link Product} entities
     */
    Page<Product> getAllProducts(Pageable pageable);

    /**
     * Updates the stock quantity of a product.
     *
     * @param id the ID of the product to update
     * @param stockUpdateDTO DTO containing the new stock quantity
     * @return the updated {@link Product} entity
     * @throws ProductNotFoundException if no product is found with the given ID
     */
    Product updateProductStock(Long id, StockUpdateDTO stockUpdateDTO) throws ProductNotFoundException;
}
