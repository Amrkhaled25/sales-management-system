package com.salesmanagement.salesmanagement.sellers.repository;

import com.salesmanagement.salesmanagement.sellers.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
