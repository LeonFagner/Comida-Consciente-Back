package com.example.demo.service;

import com.example.demo.dto.RankingDTO;
import com.example.demo.model.Donation;
import com.example.demo.model.Point;
import com.example.demo.model.User;
import com.example.demo.repository.DonationRepository;
import com.example.demo.repository.PointRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.enums.ActionType;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final PointRepository pointRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;


    public Integer getUserScore(String userId) {
        return pointRepository.getTotalPointsByUser(userId);
    }


    public List<Point> getHistory(String userId) {
        return pointRepository.findByUser_Id(userId);
    }

    // 🔥 CALCULAR PONTOS
    @Transactional
    public void calculateScore(String donationId) {

        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));

        User user = donation.getDonor();

        int points = calculatePoints(donation);

        Point point = new Point();
        point.setUser(user);
        point.setDonation(donation);
        point.setPoints(points);
        point.setActionType(ActionType.DONATION_COLLECTED);

        pointRepository.save(point);
    }
    public List<RankingDTO> getRanking() {
        return pointRepository.getRanking();
    }


    private int calculatePoints(Donation donation) {

        int base = 10;

        if (donation.isUrgent()) {
            base += 20;
        }

        if (donation.getQuantity().compareTo(BigDecimal.valueOf(10)) > 0) {
            base += 15;
        }

        return base;
    }
}