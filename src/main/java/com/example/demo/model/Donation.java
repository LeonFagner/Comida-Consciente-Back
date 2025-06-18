package com.example.demo.model;

import com.example.demo.enums.DonationCategory;
import com.example.demo.enums.DonationStatus;
import com.example.demo.enums.UnitOfMeasure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name =  "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private User donor;

    private String itemName;

    @Enumerated(EnumType.STRING)
    private DonationCategory category;

    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure;

    private LocalDate expirationDate;

    @Column(columnDefinition = "TEXT")
    private String pickupAddress;

    @Column(columnDefinition = "TEXT")
    private String availableTimes;

    private String pickupContact;

    @Column(columnDefinition = "TEXT")
    private String notes;

    private boolean urgent = false;

    @Enumerated(EnumType.STRING)
    private DonationStatus status;

    private BigDecimal latitude;
    private BigDecimal longitude;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
