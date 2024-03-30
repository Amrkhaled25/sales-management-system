package com.salesmanagement.salesmanagement.reporting.service;

import com.salesmanagement.salesmanagement.clients.management.model.Client;
import com.salesmanagement.salesmanagement.clients.management.repository.ClientRepository;
import com.salesmanagement.salesmanagement.products.management.model.Product;
import com.salesmanagement.salesmanagement.products.management.repository.ProductRepository;
import com.salesmanagement.salesmanagement.reporting.dto.ClientReportResponseDto;
import com.salesmanagement.salesmanagement.reporting.dto.ProductReportResponseDto;
import com.salesmanagement.salesmanagement.reporting.dto.SalesReportResponseDto;
import com.salesmanagement.salesmanagement.sales.model.Sales;
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
    public ClientReportResponseDto getClientReport() {
        long totalClients = clientRepository.count();
        List<Client> topSpendingClients = salesRepository.findTopSpendingClients();
        List<Sales> clientsSales = salesRepository.findClientsSales();
        List<Object[]> locationStatistics = salesRepository.findLocationStatistics();
        return ClientReportResponseDto.builder()
                .totalClients(totalClients)
                .topSpendingClients(topSpendingClients)
                .clientsSales(clientsSales)
                .locationStatistics(locationStatistics)
                .build();
    }

    public ProductReportResponseDto getProductReport(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        Long numberOfSales = transactionsRepository.countByProduct(id);
        Double priceStatistics = productRepository.getPriceStatistics(product.getCategory());
        String priceStatus =  (priceStatistics > product.getPrice()) ?   "Above Average + "+(priceStatistics-product.getPrice())/100.0+"%" : "Below Average - "+(product.getPrice()-priceStatistics)/100.0+"%";

        return ProductReportResponseDto.builder()
                .getProductStatus(product)
                .numberOfSales(numberOfSales)
                .priceStatistics(priceStatus)
                .build();
    }

    public Date parseDate(String date)throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date resultDate = dateFormat.parse(date);
        return resultDate ;
    }
}
