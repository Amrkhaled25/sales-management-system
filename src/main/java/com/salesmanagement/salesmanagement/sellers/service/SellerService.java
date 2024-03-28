package com.salesmanagement.salesmanagement.sellers.service;

import com.salesmanagement.salesmanagement.sellers.dto.SellerRequestDto;
import com.salesmanagement.salesmanagement.sellers.model.Seller;
import com.salesmanagement.salesmanagement.sellers.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public void createSeller(SellerRequestDto request) {
        Seller seller = Seller.builder()
                .name(request.getName())
                .build();
        sellerRepository.save(seller);
    }
}
