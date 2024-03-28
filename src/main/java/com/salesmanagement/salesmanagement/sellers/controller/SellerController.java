package com.salesmanagement.salesmanagement.sellers.controller;

import com.salesmanagement.salesmanagement.clients.management.dto.ClientData;
import com.salesmanagement.salesmanagement.clients.management.dto.ClientResponseDto;
import com.salesmanagement.salesmanagement.sellers.dto.SellerData;
import com.salesmanagement.salesmanagement.sellers.dto.SellerRequestDto;
import com.salesmanagement.salesmanagement.sellers.dto.SellerResponseDto;
import com.salesmanagement.salesmanagement.sellers.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService ;
    @GetMapping("/all-sellers")
    public ResponseEntity<SellerResponseDto> getAllSellers() {
        try{
            var sellers = sellerService.getAllSellers();
            return ResponseEntity.ok().body(SellerResponseDto.builder().
                    message("All sellers fetched successfully").
                    data(
                            SellerData.builder().
                                    sellers(sellers).
                                    build()
                    ).
                    build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(SellerResponseDto.builder().
                    message(e.getMessage()).
                    data(SellerData.builder().build()).
                    build());
        }
    }

    @PostMapping("/create-seller")
    public ResponseEntity<SellerResponseDto> createSeller(@RequestBody SellerRequestDto request) {
        try{
            sellerService.createSeller(request);
            return ResponseEntity.ok().body(SellerResponseDto.builder().
                    message("Seller created successfully").
                    data(
                            new SellerData()
                    ).
                    build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(SellerResponseDto.builder().
                    message(e.getMessage()).
                    data(SellerData.builder().build()).
                    build());
        }
    }
}
