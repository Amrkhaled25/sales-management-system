package com.salesmanagement.salesmanagement.products.management.service;

import com.salesmanagement.salesmanagement.products.management.dto.ProductRequestDto;
import com.salesmanagement.salesmanagement.products.management.model.Product;
import com.salesmanagement.salesmanagement.products.management.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public void createProduct(ProductRequestDto request) {
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .category(request.getCategory())
                .creation_date(request.getCreationDate())
                .available_quantity(request.getInitialQuantity())
                .description(request.getDescription())
                .seller(request.getSeller())
                .build();

        isProductValid(product);
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product updateProduct(Long id, ProductRequestDto request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        if(isPropertyValid(request.getName()))product.setName(request.getName());
        if(isPropertyValid(request.getPrice()))product.setPrice(request.getPrice());
        if(isPropertyValid(request.getCategory()))product.setCategory(request.getCategory());
        if(isPropertyValid(request.getAvailableQuantity()))product.setAvailable_quantity(product.getAvailable_quantity() + request.getAvailableQuantity());
        if(isPropertyValid(request.getDescription()))product.setDescription(request.getDescription());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public void isProductValid(Product product) {
        if (!isPropertyValid(product.getName())) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (!isPropertyValid(product.getCategory())) {
            throw new IllegalArgumentException("Product category is required");
        }
        if (!isPropertyValid(product.getDescription())) {
            throw new IllegalArgumentException("Product description is required");
        }
        if (!isPropertyValid(product.getAvailable_quantity())) {
            throw new IllegalArgumentException("Product available quantity is required");
        }
        if (!isPropertyValid(product.getPrice())) {
            throw new IllegalArgumentException("Product price is required");
        }
    }

    public Boolean isPropertyValid(String property) {
        return property != null && !property.isEmpty();
    }
    public Boolean isPropertyValid(double property) {
        return property >=0;
    }
    public Boolean isPropertyValid(int property) {
        return property >=0;
    }
}
