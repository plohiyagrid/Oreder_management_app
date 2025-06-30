package com.griddynamics.order_management.service.impl;

import com.griddynamics.order_management.dto.ProductDTO;
import com.griddynamics.order_management.dto.StockUpdateDTO;
import com.griddynamics.order_management.exception.ProductNotFoundException;
import com.griddynamics.order_management.model.Product;
import com.griddynamics.order_management.repository.ProductRepository;
import com.griddynamics.order_management.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the {@link ProductService} interface, providing business logic
 * for creating, retrieving, and updating products and their stock quantities.
 * <p>
 * This service layer uses {@link ProductRepository} for persistence and
 * {@link ModelMapper} to map between DTOs and entity objects.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    /**
     * Repository for performing CRUD operations on {@link Product} entities.
     */
    private final ProductRepository productRepository;

    /**
     * Mapper for converting between DTOs and entities.
     */
    private final ModelMapper modelMapper;

    /**
     * Creates a new product in the system.
     *
     * @param productDTO the DTO containing product data
     * @return the persisted {@link Product} entity
     */
    @Override
    @Transactional
    public Product createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        return productRepository.save(product);
    }

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id the ID of the product to retrieve
     * @return the corresponding {@link Product} entity
     * @throws ProductNotFoundException if the product does not exist
     */
    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    /**
     * Retrieves all products available in the system.
     *
     * @return list of {@link Product} entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves all products available in the system with pagination.
     *
     * @param pageable the pagination information
     * @return a page of {@link Product} entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Updates the stock quantity of an existing product.
     *
     * @param id the ID of the product to update
     * @param stockUpdateDTO the DTO containing the new stock quantity
     * @return the updated {@link Product} entity
     * @throws ProductNotFoundException if the product does not exist
     */
    @Override
    @Transactional
    public Product updateProductStock(Long id, StockUpdateDTO stockUpdateDTO) throws ProductNotFoundException {
        Product product = getProductById(id);
        product.setStockQuantity(stockUpdateDTO.getStockQuantity());
        return productRepository.save(product);
    }
}
