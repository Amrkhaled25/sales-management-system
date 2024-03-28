package com.salesmanagement.salesmanagement.clients.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesmanagement.salesmanagement.clients.management.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ClientData {
    private String name ;
    private String lastname ;
    private String mobile ;
    private String email ;
    private String address ;
    private Client client ;
    private List<Client> clients ;
}
