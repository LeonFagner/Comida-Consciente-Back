package com.example.demo.controller;

import com.example.demo.dto.RankingDTO;
import com.example.demo.model.Point;
import com.example.demo.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService service;

    @GetMapping("/user/{id}")
    public ResponseEntity<Integer> getUserScore(@PathVariable String id) {
        return ResponseEntity.ok(service.getUserScore(id));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Point>> getHistory(@PathVariable String userId) {
        return ResponseEntity.ok(service.getHistory(userId));
    }

    @PostMapping("/calculate/{donationId}")
    public ResponseEntity<Void> calculate(@PathVariable String donationId) {
        service.calculateScore(donationId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/ranking")
    public ResponseEntity<List<RankingDTO>> getRanking() {
        return ResponseEntity.ok(service.getRanking());
    }
}