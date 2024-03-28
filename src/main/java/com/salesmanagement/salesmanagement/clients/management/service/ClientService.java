package com.salesmanagement.salesmanagement.clients.management.service;

import com.salesmanagement.salesmanagement.clients.management.dto.ClientRequestDto;
import com.salesmanagement.salesmanagement.clients.management.model.Client;
import com.salesmanagement.salesmanagement.clients.management.repository.ClientRepository;
import com.salesmanagement.salesmanagement.products.management.model.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public void createClient(ClientRequestDto request){
        isClientValid(request);
        if(!isEmailValid(request.getEmail())){
            throw new IllegalArgumentException("Email is not valid or already exists");
        }
        Client client = Client.builder()
                .name(request.getName())
                .lastname(request.getLastname())
                .mobile(request.getMobile())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();
        clientRepository.save(client);
    }

    @Transactional
    public Client updateClient(Long id, ClientRequestDto request){
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
        if(isPropertyValid(request.getName()))client.setName(request.getName());
        if(isPropertyValid(request.getLastname()))client.setLastname(request.getLastname());
        if(isPropertyValid(request.getMobile()))client.setMobile(request.getMobile());
        if(isPropertyValid(request.getEmail())){
            if(!isEmailValid(request.getEmail())){
                throw new IllegalArgumentException("Email is not valid or already exists");
            }
            client.setEmail(request.getEmail());
        }
        if(isPropertyValid(request.getAddress()))client.setAddress(request.getAddress());

        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public void isClientValid(ClientRequestDto client) {
        if (!isPropertyValid(client.getName())) {
            throw new IllegalArgumentException("Client name is required");
        }
        if (!isPropertyValid(client.getLastname())) {
            throw new IllegalArgumentException("Client lastname is required");
        }
        if (!isPropertyValid(client.getMobile())) {
            throw new IllegalArgumentException("Client mobile is required");
        }
        if (!isPropertyValid(client.getEmail())) {
            throw new IllegalArgumentException("Client email is required");
        }
        if (!isPropertyValid(client.getAddress())) {
            throw new IllegalArgumentException("Client address is required");
        }

    }
    public Boolean isPropertyValid(String property) {
        return (property != null && !property.isEmpty());
    }

    public Boolean isEmailValid(String email) {
        var client = clientRepository.findByEmail(email);
        System.out.println("Client: " + client);
        return ( email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                && client.isEmpty()
        ) ;
    }

}
