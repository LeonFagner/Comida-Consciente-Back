package com.example.demo.model;

import com.example.demo.enums.InstitutionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "ngos")
public class Ngo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    private InstitutionType institutionType;

    private int storageCapacity;

    private int peopleServed;

    @Column(columnDefinition = "json")
    private String verificationDocuments;
}
