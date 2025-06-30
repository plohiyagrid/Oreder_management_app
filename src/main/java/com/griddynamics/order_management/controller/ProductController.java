package com.griddynamics.order_management.controller;

import com.griddynamics.order_management.dto.ProductDTO;
import com.griddynamics.order_management.dto.StockUpdateDTO;
import com.griddynamics.order_management.exception.ProductNotFoundException;
import com.griddynamics.order_management.model.Product;
import com.griddynamics.order_management.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing product-related operations.
 * <p>
 * Provides endpoints to create new products, list all products,
 * and update the stock quantity of a product.
 * </p>
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    /**
     * Service layer for handling product operations.
     */
    private final ProductService productService;

    /**
     * Creates a new product in the system.
     *
     * @param productDTO DTO containing the product's details
     * @return the created {@link Product} and HTTP 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productService.createProduct(productDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all products available in the system.
     *
     * @return list of {@link Product} entities and HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Retrieves a paginated list of products available in the system.
     *
     * @param pageable the pagination information (page, size, sort)
     * @return page of {@link Product} entities and HTTP 200 (OK)
     */
    @GetMapping("/paged")
    public ResponseEntity<Page<Product>> getAllProductsPaged(Pageable pageable) {
        Page<Product> products = productService.getAllProducts(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Updates the stock quantity of a specific product.
     *
     * @param id the ID of the product to update
     * @param stockUpdateDTO DTO containing the new stock quantity
     * @return the updated {@link Product} and HTTP 200 (OK)
     * @throws ProductNotFoundException if the product is not found
     */
    @PutMapping("/{id}/stock")
    public ResponseEntity<Product> updateProductStock(
            @PathVariable Long id,
            @Valid @RequestBody StockUpdateDTO stockUpdateDTO
    ) throws ProductNotFoundException {
        Product product = productService.updateProductStock(id, stockUpdateDTO);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
