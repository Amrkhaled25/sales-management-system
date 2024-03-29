package com.salesmanagement.salesmanagement.reporting.dto;

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
public class SalesReportResponseDto {
    private long totalSales ;
    private Double totalRevenue ;
    private List<Product> topSellingProducts ;
    private List<Seller> topPerformingSellers ;
}
