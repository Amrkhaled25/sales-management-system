package com.salesmanagement.salesmanagement.clients.management.service;

import com.salesmanagement.salesmanagement.clients.management.dto.ClientRequestDto;
import com.salesmanagement.salesmanagement.clients.management.model.Client;
import com.salesmanagement.salesmanagement.clients.management.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllClients() {
        assertNotNull(clientService.getAllClients());
    }

    @Test
    void testCreateClient() {
        ClientRequestDto request = new ClientRequestDto("John", "Doe", "1234567890", "jphn@gmail.com", "123 Street");
        when(clientRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> clientService.createClient(request));
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testUpdateClient() {
        ClientRequestDto request = new ClientRequestDto();
        Client client = new Client();
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));

        clientService.updateClient(1L, request);

        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testDeleteClient() {
        clientService.deleteClient(1L);

        verify(clientRepository, times(1)).deleteById(1L);
    }

    @Test
    void testIsClientValid() {
        ClientRequestDto client = new ClientRequestDto();
        client.setName("John");
        client.setLastname("Doe");
        client.setMobile("1234567890");
        client.setEmail("john.doe@example.com");
        client.setAddress("123 Street");

        assertDoesNotThrow(() -> clientService.isClientValid(client));
    }

    @Test
    void testIsPropertyValid() {
        assertTrue(clientService.isPropertyValid("valid"));
        assertFalse(clientService.isPropertyValid(""));
        assertFalse(clientService.isPropertyValid(null));
    }

    @Test
    void testIsEmailValid() {
        when(clientRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertTrue(clientService.isEmailValid("john.doe@example.com"));
        assertFalse(clientService.isEmailValid("invalid"));
    }
}