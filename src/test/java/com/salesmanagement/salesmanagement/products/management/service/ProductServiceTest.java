package com.salesmanagement.salesmanagement.products.management.service;

import com.salesmanagement.salesmanagement.products.management.dto.ProductRequestDto;
import com.salesmanagement.salesmanagement.products.management.model.Product;
import com.salesmanagement.salesmanagement.products.management.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        assertNotNull(productService.getAllProducts());
    }

    @Test
    void testCreateProduct() {
        ProductRequestDto request = ProductRequestDto.builder().
                name("Product1").
                description("Description1").
                category("Category1").
                availableQuantity(10).
                price(100.0).
                build() ;
        when(productRepository.save(any(Product.class))).thenReturn(new Product());
        assertDoesNotThrow(() -> productService.createProduct(request));

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdateProduct() {
        ProductRequestDto request = new ProductRequestDto();
        Product product = new Product();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        productService.updateProduct(1L, request);

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testIsProductValid() {
        Product product = new Product();
        product.setName("Product1");
        product.setCategory("Category1");
        product.setDescription("Description1");
        product.setAvailable_quantity(10);
        product.setPrice(100.0);

        assertDoesNotThrow(() -> productService.isProductValid(product));
    }

    @Test
    void testIsPropertyValid() {
        assertTrue(productService.isPropertyValid("valid"));
        assertFalse(productService.isPropertyValid(""));
        assertFalse(productService.isPropertyValid(null));
        assertTrue(productService.isPropertyValid(10));
        assertFalse(productService.isPropertyValid(-1));
        assertTrue(productService.isPropertyValid(100.0));
        assertFalse(productService.isPropertyValid(-100.0));
    }
}