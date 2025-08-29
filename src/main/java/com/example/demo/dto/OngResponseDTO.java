package com.example.demo.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OngResponseDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;

    private String cnpj;
    private String institutionType;
    private Integer storageCapacity;
    private Integer peopleServed;
    private String verificationDocument;


}
