package com.example.demo.model;

import com.example.demo.enums.EstablishmentType;
import com.example.demo.enums.RecognitionLevel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("ESTABLISHMENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Establishment extends User {

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(name = "trade_name")
    private String tradeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "establishment_type")
    private EstablishmentType  establishmentType;

    @Column(name = "opening_hours", columnDefinition = "json")
    private String openingHours;

    @Column(name = "total_score")
    private int totalScore = 0;



    @Enumerated(EnumType.STRING)
    @Column(name = "recognition_level")
    private RecognitionLevel recognitionLevel;




}