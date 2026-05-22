package com.example.demo.repository;

import com.example.demo.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeoRepository extends JpaRepository<Donation, String> {

    List<Donation> findAll();
}