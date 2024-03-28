package com.salesmanagement.salesmanagement.clients.management.repository;

import com.salesmanagement.salesmanagement.clients.management.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
   Optional<Client> findByEmail (String email);
}
