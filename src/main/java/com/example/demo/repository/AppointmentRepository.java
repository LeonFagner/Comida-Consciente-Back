package com.example.demo.repository;

import com.example.demo.enums.AppointmentStatus;
import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    List<Appointment> findByRequester_Id(String requesterId);

    List<Appointment> findByStatus(AppointmentStatus status);

    boolean existsByDonation_IdAndRequester_Id(String donationId, String requesterId);
}