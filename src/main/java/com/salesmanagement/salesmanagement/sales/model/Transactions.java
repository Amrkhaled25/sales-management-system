package com.salesmanagement.salesmanagement.sales.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesmanagement.salesmanagement.products.management.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;
    private Double price;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
