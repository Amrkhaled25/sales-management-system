package com.salesmanagement.salesmanagement.clients.management.controller;


import com.salesmanagement.salesmanagement.clients.management.dto.ClientData;
import com.salesmanagement.salesmanagement.clients.management.dto.ClientRequestDto;
import com.salesmanagement.salesmanagement.clients.management.dto.ClientResponseDto;
import com.salesmanagement.salesmanagement.clients.management.model.Client;
import com.salesmanagement.salesmanagement.clients.management.service.ClientService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService ;
    @GetMapping("/all-clients")
    public ResponseEntity<ClientResponseDto> getAllClients() {
        try{
            var clients = clientService.getAllClients();
            return ResponseEntity.ok().body(ClientResponseDto.builder().
                    message("All clients fetched successfully").
                    data(
                            ClientData.builder().clients(clients).build()
                    ).
                    build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(ClientResponseDto.builder().
                    message(e.getMessage()).
                    data(ClientData.builder().build()).
                    build());
        }
    }

    @PostMapping("/create-client")
    public ResponseEntity<ClientResponseDto> createClient(@RequestBody ClientRequestDto request) {
        try {
            clientService.createClient(request);
            return  ResponseEntity.ok().body(ClientResponseDto.builder().
                    message("Client has been saved successfully").
                    data(ClientData.builder().build()).
                    build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(ClientResponseDto.builder().
                    message(e.getMessage()).
                    data(ClientData.builder().build()).
                    build());
        }

    }

    @PutMapping("/update-client/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(@PathVariable Long id, @RequestBody ClientRequestDto request) {
        try {
            Client client = clientService.updateClient(id, request);
            return ResponseEntity.ok().body(ClientResponseDto.builder().
                    message("Client has been updated successfully").
                    data(ClientData.builder().client(client).build()).
                    build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(ClientResponseDto.builder().
                    message(e.getMessage()).
                    data(ClientData.builder().build()).
                    build());
        }

    }

    @DeleteMapping("/delete-client/{id}")
    public ResponseEntity<ClientResponseDto> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.ok().body(ClientResponseDto.builder().
                    message("Client has been deleted successfully").
                    data(ClientData.builder().build()).
                    build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(ClientResponseDto.builder().
                    message(e.getMessage()).
                    data(ClientData.builder().build()).
                    build());
        }

    }
}
