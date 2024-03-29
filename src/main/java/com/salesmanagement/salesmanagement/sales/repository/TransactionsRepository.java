package com.salesmanagement.salesmanagement.sales.repository;

import com.salesmanagement.salesmanagement.sales.model.Transactions;
import com.salesmanagement.salesmanagement.sellers.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    @Query("SELECT p.seller FROM Sales s JOIN s.transactions t JOIN t.product p WHERE s.creationDate BETWEEN :startDate AND :endDate GROUP BY p.seller ORDER BY SUM(t.quantity) DESC")
    List<Seller> findTopPerformingSellers(Date startDate, Date endDate);
}
