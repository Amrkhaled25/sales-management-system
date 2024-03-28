package com.salesmanagement.salesmanagement.products.management.controller;

import com.salesmanagement.salesmanagement.products.management.service.ProductService;
import com.salesmanagement.salesmanagement.products.management.dto.ProductData;
import com.salesmanagement.salesmanagement.products.management.dto.ProductRequestDto;
import com.salesmanagement.salesmanagement.products.management.dto.ProductResponseDto;
import com.salesmanagement.salesmanagement.products.management.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all-products")
    public ResponseEntity<ProductResponseDto> getAllProducts() {
        try{
            var products = productService.getAllProducts();
            return ResponseEntity.ok().body(ProductResponseDto.builder().
                    message("All products fetched successfully").
                    data(
                            ProductData.builder().products(products).build()
                    ).
                    build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(ProductResponseDto.builder().
                    message(e.getMessage()).
                    data(ProductData.builder().build()).
                    build());
        }
    }

    @PostMapping("/create-product")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto request) {
        try {
            productService.createProduct(request);
            return  ResponseEntity.ok().body(ProductResponseDto.builder().
                    message("Product has been saved successfully").
                    data(ProductData.builder().build()).
                    build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(ProductResponseDto.builder().
                    message(e.getMessage()).
                    data(ProductData.builder().build()).
                    build());
        }

    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto request) {
        try {
            System.out.println("Request: " + request);
            Product product = productService.updateProduct(id, request);
            return ResponseEntity.ok().body(ProductResponseDto.builder().
                    message("Product has been updated successfully").
                    data(ProductData.builder().product(product).build()).
                    build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(ProductResponseDto.builder().
                    message(e.getMessage()).
                    data(ProductData.builder().build()).
                    build());
        }
    }

    @DeleteMapping("delete-product/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok().body(ProductResponseDto.builder().
                    message("Product has been deleted successfully").
                    data(ProductData.builder().build()).
                    build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(ProductResponseDto.builder().
                    message(e.getMessage()).
                    data(ProductData.builder().build()).
                    build());
        }
    }
}
