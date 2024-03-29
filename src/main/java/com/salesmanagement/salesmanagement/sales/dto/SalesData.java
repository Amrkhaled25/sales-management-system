package com.salesmanagement.salesmanagement.sales.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesmanagement.salesmanagement.clients.management.model.Client;
import com.salesmanagement.salesmanagement.sales.model.Sales;
import com.salesmanagement.salesmanagement.sales.model.Transactions;
import com.salesmanagement.salesmanagement.sellers.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SalesData {
    private Long id ;
    private Date creationDate ;
    private Client client ;
    private Seller seller ;
    private Double total ;
    private List<Sales> sales ;

}
