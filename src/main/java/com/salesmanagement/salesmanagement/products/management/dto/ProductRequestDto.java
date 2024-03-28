package com.salesmanagement.salesmanagement.products.management.dto;

import com.salesmanagement.salesmanagement.sellers.model.Seller;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductRequestDto {
    private String name ;
    private String description ;
    private String category ;
    private Date creationDate ;
    private int availableQuantity ;
    private int initialQuantity ;
    private double price ;
    private Seller seller ;
}
