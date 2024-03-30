package com.salesmanagement.salesmanagement.reporting.controller;

import com.salesmanagement.salesmanagement.reporting.dto.ClientReportResponseDto;
import com.salesmanagement.salesmanagement.reporting.dto.ProductReportResponseDto;
import com.salesmanagement.salesmanagement.reporting.dto.SalesReportRequestDto;
import com.salesmanagement.salesmanagement.reporting.dto.SalesReportResponseDto;
import com.salesmanagement.salesmanagement.reporting.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    @PostMapping("/sales-report")
    public ResponseEntity<SalesReportResponseDto> getSalesReport(@RequestBody SalesReportRequestDto request ){
        try{
            Date startDate = reportService.parseDate(request.getStartDate());
            Date endDate = reportService.parseDate(request.getEndDate());
            return ResponseEntity.ok(reportService.getSalesReport(startDate,endDate));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/client-report")
    public ResponseEntity<ClientReportResponseDto> getClientReport(){
        try{
            return ResponseEntity.ok(reportService.getClientReport());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/product-report/{id}")
    public ResponseEntity<ProductReportResponseDto> getProductReport(@PathVariable Long id ){
        try{
            return ResponseEntity.ok(reportService.getProductReport(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

}
