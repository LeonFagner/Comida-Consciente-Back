package com.example.demo.repository;

import com.example.demo.dto.RankingDTO;
import com.example.demo.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, String> {

    List<Point> findByUser_Id(String userId);

    @Query("SELECT COALESCE(SUM(p.points), 0) FROM Point p WHERE p.user.id = :userId")
    Integer getTotalPointsByUser(@Param("userId") String userId);

    @Query("""
        SELECT new com.example.demo.dto.RankingDTO(
            p.user.id,
            p.user.name,
            SUM(p.points)
        )
        FROM Point p
        GROUP BY p.user.id, p.user.name
        ORDER BY SUM(p.points) DESC
    """)
    List<RankingDTO> getRanking();
}