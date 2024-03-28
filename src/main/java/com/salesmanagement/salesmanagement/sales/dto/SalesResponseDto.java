package com.salesmanagement.salesmanagement.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesResponseDto {
    private String message;
    private SalesData data ;
}
