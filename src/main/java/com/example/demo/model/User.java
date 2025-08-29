package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public  class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36, columnDefinition = "char(36)")
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String name;

    private String cpf;

    private String personalInterest;

    private String phone;

    private String address;

    private boolean active = true;

    private String resetToken;
    private LocalDateTime resetTokenExpiration;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public String getUserType() {
        if (this instanceof Admin) return "ADMIN";
        if (this instanceof Establishment) return "ESTABLISHMENT";
        if (this instanceof Person) return "PERSON";
        if (this instanceof Ong) return "ONG";
        return "UNKNOWN";
    }
}
