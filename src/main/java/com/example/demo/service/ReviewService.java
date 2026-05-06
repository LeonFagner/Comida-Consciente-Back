package com.example.demo.service;

import com.example.demo.dto.ReviewRequestDTO;
import com.example.demo.dto.ReviewResponseDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    // 🔥 CREATE
    public ReviewResponseDTO create(ReviewRequestDTO dto) {

        Appointment appointment = appointmentRepository.findById(dto.appointmentId())
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        User reviewer = userRepository.findById(dto.reviewerId())
                .orElseThrow(() -> new RuntimeException("Usuário avaliador não encontrado"));

        User reviewed = userRepository.findById(dto.reviewedId())
                .orElseThrow(() -> new RuntimeException("Usuário avaliado não encontrado"));

        Review review = new Review();
        review.setAppointment(appointment);
        review.setReviewer(reviewer);
        review.setReviewed(reviewed);
        review.setRating(dto.rating());
        review.setComment(dto.comment());

        Review saved = repository.save(review);

        return toDTO(saved);
    }

    // 🔹 POR AGENDAMENTO
    public List<ReviewResponseDTO> getByAppointment(String id) {
        return repository.findByAppointment_Id(id)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // 🔹 POR USUÁRIO (avaliado)
    public List<ReviewResponseDTO> getByUser(String id) {
        return repository.findByReviewed_Id(id)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // 🔹 POR AVALIADOR (extra)
    public List<ReviewResponseDTO> getByReviewer(String id) {
        return repository.findByReviewer_Id(id)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // 🔄 UPDATE
    public ReviewResponseDTO update(String id, ReviewRequestDTO dto) {

        Review review = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        review.setRating(dto.rating());
        review.setComment(dto.comment());

        Review updated = repository.save(review);

        return toDTO(updated);
    }

    // ❌ DELETE
    public void delete(String id) {
        repository.deleteById(id);
    }

    // 🔁 MAPPER
    private ReviewResponseDTO toDTO(Review r) {
        return new ReviewResponseDTO(
                r.getId(),
                r.getReviewer().getName(),
                r.getReviewed().getName(),
                r.getRating(),
                r.getComment(),
                r.getCreatedAt()
        );
    }
}