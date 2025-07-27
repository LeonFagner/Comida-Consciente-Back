package com.example.demo.service;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.enums.UserType;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        if (userRequest.getUserType() == null || userRequest.getUserType().isBlank()) {
            throw new IllegalArgumentException("O campo 'userType' é obrigatório e não pode ser nulo ou vazio.");
        }
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        User user = modelMapper.map(userRequest, User.class);
        user.setId(null);
        user.setPasswordHash(passwordEncoder.encode(userRequest.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);

        user.setUserType(UserType.valueOf(userRequest.getUserType().toUpperCase()));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }


    public UserResponseDTO getUserById(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User "+ id +" not found "  ));
        return modelMapper.map(user, UserResponseDTO.class);
    }



    public UserResponseDTO updateUser(String id, UserRequestDTO userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User "+ id +" not found " ));

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());
        user.setUserType(UserType.valueOf(userRequest.getUserType()));

        user.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserResponseDTO.class);
    }

    public void deleteUser(String id) {

        if (!userRepository.existsById(id)){
            throw new RuntimeException("User "+ id +" not found " );
        }
        userRepository.deleteById(id);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .toList();
    }




    public String login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        return "Login successful for user ID: " + user.getId();
    }
}
