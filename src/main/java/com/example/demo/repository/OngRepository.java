package com.example.demo.repository;



import com.example.demo.model.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OngRepository extends JpaRepository<Ong, String> {
    List<Ong> findAll();
    List<Ong>findAllById(String id);
}
