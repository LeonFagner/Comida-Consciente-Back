package com.example.demo.repository;

import com.example.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {

    List<Review> findByAppointment_Id(String appointmentId);

    List<Review> findByReviewed_Id(String reviewedId);

    List<Review> findByReviewer_Id(String reviewerId);
}