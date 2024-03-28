package com.salesmanagement.salesmanagement.sales.repository;

import com.salesmanagement.salesmanagement.sales.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {

}
