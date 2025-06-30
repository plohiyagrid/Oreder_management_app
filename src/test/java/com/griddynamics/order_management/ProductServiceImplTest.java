package com.griddynamics.order_management;

import com.griddynamics.order_management.exception.ProductNotFoundException;
import com.griddynamics.order_management.model.Product;
import com.griddynamics.order_management.repository.ProductRepository;
import com.griddynamics.order_management.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testGetProductById_ProductExists() throws ProductNotFoundException {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);
        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        verify(productRepository).findById(1L);
    }

    @Test
    void testGetProductById_ProductNotFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(2L));
        verify(productRepository).findById(2L);
    }
} 