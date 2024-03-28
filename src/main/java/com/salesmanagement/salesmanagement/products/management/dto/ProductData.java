package com.salesmanagement.salesmanagement.products.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesmanagement.salesmanagement.products.management.model.Product;
import com.salesmanagement.salesmanagement.sellers.model.Seller;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ProductData {
    private Long id ;
    private String name ;
    private String description ;
    private String category ;
    private Date creation_date ;
    private int available_quantity ;
    private double price ;
    private List<Product> products ;
    private Product product ;
    private Seller seller ;
}
