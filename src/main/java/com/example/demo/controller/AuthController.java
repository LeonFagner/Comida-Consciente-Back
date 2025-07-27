package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO request){
        UserResponseDTO createdUser = userService.createUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        authService.logout(token);
        return ResponseEntity.ok("Logout realizado com sucesso.");
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getLoggedUser(@RequestHeader("Authorization") String token) {
        String cleanedToken = token.replace("Bearer ", "").trim();
        User user = authService.getUserFromToken(cleanedToken);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserResponseDTO> updateProfile(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateUserDTO dto) {

        String cleanedToken = token.replace("Bearer ", "").trim();
        User user = authService.updateUserProfile(cleanedToken, dto);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordDTO dto) {
        authService.sendRecoveryEmail(dto.getEmail());
        return ResponseEntity.ok("Recovery email sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO dto) {
        authService.resetPassword(dto.getToken(), dto.getNewPassword());
        return ResponseEntity.ok("Password successfully reset");
    }






}

