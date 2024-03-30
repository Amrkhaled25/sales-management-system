package com.salesmanagement.salesmanagement.products.management.repository;

import com.salesmanagement.salesmanagement.products.management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT AVG(p.price) FROM Product p WHERE p.category = :category")
    Double getPriceStatistics(String category);
}
