package com.example.demo.model;

import com.example.demo.enums.ActionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "points")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Donation donation;

    private int points;

    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
