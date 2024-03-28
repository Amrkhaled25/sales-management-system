package com.salesmanagement.salesmanagement.sellers.model;

import com.salesmanagement.salesmanagement.products.management.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String name ;
    @OneToMany(mappedBy = "seller" , cascade = CascadeType.ALL)
    private List<Product> products ;
}
