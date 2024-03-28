package com.salesmanagement.salesmanagement.products.management.repository;

import com.salesmanagement.salesmanagement.products.management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
