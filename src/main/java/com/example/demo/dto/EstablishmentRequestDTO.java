package com.example.demo.dto;


import lombok.Data;

@Data
public class EstablishmentRequestDTO {
    private String email;
    private String password;
    private String name;
    private String cnpj;
    private String tradeName;
    private String establishmentType;
    private String openingHours;

}
