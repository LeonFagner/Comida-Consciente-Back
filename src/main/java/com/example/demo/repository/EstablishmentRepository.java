package com.example.demo.repository;



import com.example.demo.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, String> {
    boolean existsByEmail(String email);
    boolean existsByCnpj(String cnpj);

    @Query(value = "SELECT * FROM users WHERE user_type = 'ESTABLISHMENT'", nativeQuery = true)
    List<Establishment> findAllEstablishments();


}
