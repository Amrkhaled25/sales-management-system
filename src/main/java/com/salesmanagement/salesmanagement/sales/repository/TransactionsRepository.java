package com.salesmanagement.salesmanagement.sales.repository;

import com.salesmanagement.salesmanagement.sales.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}
