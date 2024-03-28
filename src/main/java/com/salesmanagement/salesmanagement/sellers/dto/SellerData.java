package com.salesmanagement.salesmanagement.sellers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesmanagement.salesmanagement.products.management.model.Product;
import com.salesmanagement.salesmanagement.sellers.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SellerData {
    private String name ;
    private List<Seller>sellers ;
    private List<Product> products;
}
