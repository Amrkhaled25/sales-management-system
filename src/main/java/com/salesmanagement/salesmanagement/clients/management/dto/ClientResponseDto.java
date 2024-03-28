package com.salesmanagement.salesmanagement.clients.management.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClientResponseDto {
    private String message;
    private ClientData data ;
}
