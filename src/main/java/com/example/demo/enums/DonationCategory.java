package com.example.demo.enums;





import com.fasterxml.jackson.annotation.JsonCreator;

public enum DonationCategory {

    OTHER,
    VEGETABLES,
    DAIRY,
    NON_PERISHABLES,
    BREAD,
    FRUITS;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static DonationCategory fromString(String value) {
        return DonationCategory.valueOf(value.toUpperCase());
    }
}