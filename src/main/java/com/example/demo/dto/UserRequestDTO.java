package com.example.demo.dto;


import lombok.Data;


@Data
public class UserRequestDTO {

    private String email;
    private String password;
    private String userType;
    private String name;
    private String phone;
    private String address;
    private String cpf;
    private String personalInterest;

}
