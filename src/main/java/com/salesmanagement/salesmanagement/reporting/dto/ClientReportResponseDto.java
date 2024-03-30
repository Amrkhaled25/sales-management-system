package com.salesmanagement.salesmanagement.reporting.dto;

import com.salesmanagement.salesmanagement.clients.management.model.Client;
import com.salesmanagement.salesmanagement.sales.model.Sales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientReportResponseDto {
    private long totalClients ;
    private List<Client> topSpendingClients;
    private List<Sales> clientsSales;
    private List<Object[]> locationStatistics;

}
