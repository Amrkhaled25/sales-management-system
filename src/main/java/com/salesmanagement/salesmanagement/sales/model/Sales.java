package com.salesmanagement.salesmanagement.sales.model;

import com.salesmanagement.salesmanagement.clients.management.model.Client;
import com.salesmanagement.salesmanagement.sellers.model.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private Double total ;

    @OneToMany
    @JoinColumn(name = "transactions_id")
    private List<Transactions> transactions;
}
