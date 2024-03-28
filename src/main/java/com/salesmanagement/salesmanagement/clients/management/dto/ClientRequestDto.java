package com.salesmanagement.salesmanagement.clients.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClientRequestDto {
    private String name ;
        private String lastname ;
        private String mobile ;
        private String email ;
        private String address ;
}
