package com.example.demo.service;


import com.example.demo.dto.NotificationRequestDTO;
import com.example.demo.dto.NotificationResponseDTO;
import com.example.demo.model.Notification;
import com.example.demo.model.User;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public List<NotificationResponseDTO> getUserNotifications(String userId) {

        return notificationRepository
                .findByUser_IdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public void markAsRead(String id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);

        notificationRepository.save(notification);
    }

    public void markAllAsRead(String userId) {

        List<Notification> notifications =
                notificationRepository.findByUser_IdOrderByCreatedAtDesc(userId);

        notifications.forEach(notification -> notification.setRead(true));

        notificationRepository.saveAll(notifications);
    }

    public void delete(String id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notificationRepository.delete(notification);
    }

    public NotificationResponseDTO send(NotificationRequestDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notification notification = new Notification();

        notification.setUser(user);
        notification.setTitle(dto.getTitle());
        notification.setMessage(dto.getMessage());
        notification.setType(dto.getType());

        notificationRepository.save(notification);

        return toDTO(notification);
    }

    private NotificationResponseDTO toDTO(Notification notification) {

        return new NotificationResponseDTO(
                notification.getId(),
                notification.getTitle(),
                notification.getMessage(),
                notification.getType(),
                notification.isRead(),
                notification.getCreatedAt()
        );
    }
}