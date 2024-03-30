package com.salesmanagement.salesmanagement.sales.repository;

import com.salesmanagement.salesmanagement.clients.management.model.Client;
import com.salesmanagement.salesmanagement.products.management.model.Product;
import com.salesmanagement.salesmanagement.sales.model.Sales;
import com.salesmanagement.salesmanagement.sellers.model.Seller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    long countByCreationDateBetween(Date startDate, Date endDate);
    @Query("SELECT SUM(s.total) FROM Sales s WHERE s.creationDate BETWEEN :startDate AND :endDate")
    Double getTotalRevenue(Date startDate, Date endDate);

    @Query("SELECT t.product FROM Sales s JOIN s.transactions t WHERE s.creationDate BETWEEN :startDate AND :endDate GROUP BY t.product ORDER BY SUM(t.quantity) DESC")
    List<Product> findTopSellingProducts(Date startDate, Date endDate);

    @Query("SELECT s.client FROM Sales s  GROUP BY s.client ORDER BY SUM(s.total) DESC")
    List<Client> findTopSpendingClients();

    @Query("SELECT s FROM Sales s")
    List<Sales> findClientsSales();

    @Query("SELECT c.address, SUM(s.total) FROM Sales s JOIN s.client c GROUP BY c.address ORDER BY SUM(s.total) DESC")
    List<Object[]>findLocationStatistics() ;



}
