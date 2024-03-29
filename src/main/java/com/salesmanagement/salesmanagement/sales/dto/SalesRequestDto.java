package com.salesmanagement.salesmanagement.sales.dto;

import com.salesmanagement.salesmanagement.clients.management.model.Client;
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
    private Client client;
    private List<Transactions>transactions ;
}
