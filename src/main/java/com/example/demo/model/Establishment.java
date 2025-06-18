package com.example.demo.model;

import com.example.demo.enums.EstablishmentType;
import com.example.demo.enums.RecognitionLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "establishments")
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String cnpj;

    private String tradeName;

    @Enumerated(EnumType.STRING)
    private EstablishmentType establishmentType;

    @Column(columnDefinition = "json")
    private String openingHours;

    private int totalScore = 0;

    @Enumerated(EnumType.STRING)
    private RecognitionLevel recognitionLevel;
}
