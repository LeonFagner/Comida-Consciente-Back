package com.example.demo.enums;



import com.fasterxml.jackson.annotation.JsonCreator;

public enum DonationStatus {

    AVAILABLE,
    RESERVED,
    COMPLETED,
    CANCELLED, COLLECTED;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static DonationStatus fromString(String value) {
        return DonationStatus.valueOf(value.toUpperCase());
    }
}