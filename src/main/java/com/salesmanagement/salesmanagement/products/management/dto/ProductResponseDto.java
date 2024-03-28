package com.salesmanagement.salesmanagement.products.management.dto;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductResponseDto {
    private String message;
    private ProductData data ;
}
