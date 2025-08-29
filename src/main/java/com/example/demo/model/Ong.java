package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("ONG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ong extends User {

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(name = "institution_type")
    private String institutionType;

    @Column(name = "storage_capacity")
    private Integer storageCapacity;

    @Column(name = "people_served")
    private Integer peopleServed;

    @Column(name = "verification_document")
    private String verificationDocument;


}
