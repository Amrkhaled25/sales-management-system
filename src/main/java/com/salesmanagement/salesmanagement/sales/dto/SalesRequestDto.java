package com.salesmanagement.salesmanagement.sales.dto;

import com.salesmanagement.salesmanagement.products.management.model.Product;
import com.salesmanagement.salesmanagement.sales.model.Transactions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesRequestDto {
    private int quantity;
    private Double price;
    private Long clientId;
    private List<Transactions>transactions ;
}
