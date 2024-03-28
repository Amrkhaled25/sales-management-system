package com.salesmanagement.salesmanagement.products.management.model;

import com.salesmanagement.salesmanagement.sellers.model.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String name ;
    private String description ;
    private String category ;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date creation_date ;
    private int available_quantity ;
    private double price ;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller ;


}
