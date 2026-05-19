package com.example.demo.repository;

import com.example.demo.dto.*;
import com.example.demo.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ReportRepository extends JpaRepository<Donation, String> {

    @Query("""
                SELECT COUNT(d)
                FROM Donation d
            """)
    Long totalDonations();

    @Query("""
                SELECT COUNT(u)
                FROM User u
            """)
    Long totalUsers();

    @Query("""
                SELECT COUNT(a)
                FROM Appointment a
            """)
    Long totalAppointments();

    @Query("""
                SELECT COALESCE(SUM(d.quantity), 0)
                FROM Donation d
            """)
    BigDecimal totalFoodSaved();

    @Query("""
                SELECT new com.example.demo.dto.ActiveEstablishmentDTO(
                    d.donor.id,
                    d.donor.name,
                    COUNT(d)
                )
                FROM Donation d
                GROUP BY d.donor.id, d.donor.name
                ORDER BY COUNT(d) DESC
            """)
    List<ActiveEstablishmentDTO> activeEstablishments();

    @Query("""
                SELECT new com.example.demo.dto.RegionStatsDTO(
                    d.pickupAddress,
                    COUNT(d)
                )
                FROM Donation d
                GROUP BY d.pickupAddress
                ORDER BY COUNT(d) DESC
            """)
    List<RegionStatsDTO> servedRegions();

    @Query(value = """
    SELECT 
        DATE_FORMAT(created_at, '%Y-%m') AS period,
        COUNT(*) AS totalDonations
    FROM donations
    GROUP BY DATE_FORMAT(created_at, '%Y-%m')
    ORDER BY period
""", nativeQuery = true)
    List<Object[]> donationsByPeriod();
}