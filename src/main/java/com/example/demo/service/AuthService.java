package com.example.demo.service;


import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.UpdateUserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtUtil jwtUtil;

    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponseDTO(token, user.getName(), user.getEmail());
    }

    public void logout(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Token inválido ou ausente.");
        }

        try {
            String cleanedToken = token.replace("Bearer ", "").trim();
            jwtUtil.extractUsername(cleanedToken);
            System.out.println("Logout bem-sucedido.");
        } catch (Exception e) {
            throw new IllegalArgumentException("Token inválido.");
        }
    }

    public User getUserFromToken(String token) {
        String cleanedToken = token.replace("Bearer ", "").trim();
        String email = jwtUtil.extractUsername(cleanedToken);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public User updateUserProfile(String token, UpdateUserDTO dto) {
        User user = getUserFromToken(token);

        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getAddress() != null) user.setAddress(dto.getAddress());
        if (dto.getPhone() != null) user.setPhone(dto.getPhone());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());

        return userRepository.save(user);
    }


    @Autowired
    private EmailService emailService;
    public void sendRecoveryEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setResetTokenExpiration(LocalDateTime.now().plusHours(1));
        userRepository.save(user);


        emailService.send(user.getEmail(), "Recovery Token: " + token);
    }

    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        if (user.getResetTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiration(null);
        userRepository.save(user);
    }




}


