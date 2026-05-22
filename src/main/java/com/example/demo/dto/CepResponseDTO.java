package com.example.demo.dto;

import lombok.Data;

@Data
public class CepResponseDTO {

    private String cep;

    private String street;

    private String neighborhood;

    private String city;

    private String state;
}