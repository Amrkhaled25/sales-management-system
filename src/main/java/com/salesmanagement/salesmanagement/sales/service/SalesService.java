package com.salesmanagement.salesmanagement.sales.service;

import com.salesmanagement.salesmanagement.clients.management.model.Client;
import com.salesmanagement.salesmanagement.clients.management.repository.ClientRepository;
import com.salesmanagement.salesmanagement.products.management.model.Product;
import com.salesmanagement.salesmanagement.products.management.repository.ProductRepository;
import com.salesmanagement.salesmanagement.sales.dto.SalesRequestDto;
import com.salesmanagement.salesmanagement.sales.dto.SalesResponseDto;
import com.salesmanagement.salesmanagement.sales.model.Sales;
import com.salesmanagement.salesmanagement.sales.model.Transactions;
import com.salesmanagement.salesmanagement.sales.repository.SalesRepository;
import com.salesmanagement.salesmanagement.sellers.repository.SellerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    @Transactional
    public void createSales(SalesRequestDto request) {
        var client  = isClientExist(request.getClientId());
        isValidTransaction(request.getTransactions());
        var transactions = request.getTransactions();
        var total = transactions.stream().mapToDouble(transaction -> transaction.getPrice() * transaction.getQuantity()).sum();
        // update product quantity
        transactions.forEach(transaction -> {
            updateProductQuantity(transaction.getProduct(), transaction.getQuantity());
        });
        var sales  = Sales.builder().
                client(client).
                total(total).
                transactions(transactions).
                build() ;
        salesRepository.save(sales);
    }

    public Client isClientExist(Long clientId) {
        return  clientRepository.findById(clientId)
                    .orElseThrow(() -> new RuntimeException("Client not found"));
    }
    public Product isProductExist(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Boolean isValidQuantity(Product product, int quantity){
        return product.getAvailable_quantity() >= quantity;
    }

    public Boolean isValidPrice(Product product , Double price){
        return (price > 0 && product.getPrice() == price);
    }

    public void isValidTransaction(List<Transactions> transactions){
        for(Transactions transaction : transactions) {
            var product = isProductExist(transaction.getProduct().getId());
            if (!isValidQuantity(product, transaction.getQuantity()))
                throw new RuntimeException("Invalid quantity");
            if (!isValidPrice(product, transaction.getPrice()))
                throw new RuntimeException("Invalid price");
        }
    }

    public void updateProductQuantity(Product product, int quantity){
        product.setAvailable_quantity(product.getAvailable_quantity() - quantity);
        productRepository.save(product);
    }



}