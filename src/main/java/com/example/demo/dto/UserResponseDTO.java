package com.example.demo.dto;

import com.example.demo.enums.UserType;
import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@Data
public class UserResponseDTO {

    private String email;

    @JsonProperty("user_type")
    private UserType userType;

    private String id;

    private String name;

    private String phone;

    private String address;


    private boolean active;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    private String token;



    public UserResponseDTO(User user) {
        this.id = String.valueOf(user.getId());
        this.name = user.getName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.userType = user.getUserType();
        this.active = user.isActive();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.token = user.getResetToken();

    }


}
