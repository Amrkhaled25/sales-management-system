package com.salesmanagement.salesmanagement.sales.controller;

import com.salesmanagement.salesmanagement.sales.dto.SalesData;
import com.salesmanagement.salesmanagement.sales.dto.SalesRequestDto;
import com.salesmanagement.salesmanagement.sales.dto.SalesResponseDto;
import com.salesmanagement.salesmanagement.sales.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService ;
    @GetMapping("/all-sales")
    public ResponseEntity<SalesResponseDto> getAllSales() {
        try{
            var sales = salesService.getAllSales();
            return ResponseEntity.ok().body(SalesResponseDto.builder().
                    message("All sales fetched successfully").
                    data(
                            SalesData.builder().
                                    sales(sales)
                                    .build()
                    ).
                    build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(SalesResponseDto.builder().
                    message(e.getMessage()).
                    data(SalesData.builder().build()).
                    build());
        }
    }

    @PostMapping("/create-sale")
    public ResponseEntity<SalesResponseDto> createSale(@RequestBody SalesRequestDto request){
        try {
            salesService.createSales(request);
            return  ResponseEntity.ok().body(SalesResponseDto.builder().
                    message("Sales has been created successfully").
                    data(SalesData.builder().build()).
                    build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(SalesResponseDto.builder().
                    message(e.getMessage()).
                    data(SalesData.builder().build()).
                    build());
        }

    }
}
