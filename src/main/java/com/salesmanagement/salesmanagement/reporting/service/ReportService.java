package com.salesmanagement.salesmanagement.reporting.service;

import com.salesmanagement.salesmanagement.clients.management.repository.ClientRepository;
import com.salesmanagement.salesmanagement.products.management.model.Product;
import com.salesmanagement.salesmanagement.products.management.repository.ProductRepository;
import com.salesmanagement.salesmanagement.reporting.dto.SalesReportResponseDto;
import com.salesmanagement.salesmanagement.sales.repository.SalesRepository;
import com.salesmanagement.salesmanagement.sales.repository.TransactionsRepository;
import com.salesmanagement.salesmanagement.sellers.model.Seller;
import com.salesmanagement.salesmanagement.sellers.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final SalesRepository salesRepository;
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final ClientRepository clientRepository;
    private final TransactionsRepository transactionsRepository ;
    public SalesReportResponseDto getSalesReport(Date startDate, Date endDate) {
        long totalSales = salesRepository.countByCreationDateBetween(startDate, endDate);
        Double totalRevenue = salesRepository.getTotalRevenue(startDate, endDate);
        List<Product> topSellingProducts = salesRepository.findTopSellingProducts(startDate, endDate);
        List<Seller> topPerformingSellers = transactionsRepository.findTopPerformingSellers(startDate, endDate);
        return SalesReportResponseDto.builder()
                .totalSales(totalSales)
                .totalRevenue(totalRevenue)
                .topSellingProducts(topSellingProducts)
                .topPerformingSellers(topPerformingSellers)
                .build();
    }
    public Date parseDate(String date)throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date resultDate = dateFormat.parse(date);
        return resultDate ;
    }
}
