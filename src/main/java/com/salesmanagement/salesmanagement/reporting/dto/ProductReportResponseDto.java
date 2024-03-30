package com.salesmanagement.salesmanagement.reporting.dto;

import com.salesmanagement.salesmanagement.products.management.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReportResponseDto {
    private Product getProductStatus ;
    private Long numberOfSales ;
    private String priceStatistics ;
}
