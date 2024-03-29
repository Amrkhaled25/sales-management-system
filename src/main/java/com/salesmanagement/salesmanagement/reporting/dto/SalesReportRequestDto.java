package com.salesmanagement.salesmanagement.reporting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesReportRequestDto {
    private String startDate;
    private String endDate;
}
