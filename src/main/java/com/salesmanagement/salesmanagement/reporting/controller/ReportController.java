package com.salesmanagement.salesmanagement.reporting.controller;

import com.salesmanagement.salesmanagement.reporting.dto.SalesReportRequestDto;
import com.salesmanagement.salesmanagement.reporting.dto.SalesReportResponseDto;
import com.salesmanagement.salesmanagement.reporting.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
