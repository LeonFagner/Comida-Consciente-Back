package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AppointmentStatus {
    PENDING, CONFIRMED, COMPLETED, CANCELLED;

    @JsonCreator
    public static AppointmentStatus fromString(String value) {
        return AppointmentStatus.valueOf(value.toUpperCase());
    }
}
