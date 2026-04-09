package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UnitOfMeasure {
    KG, G, LITERS, UNITS, BOXES;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static UnitOfMeasure fromString(String value) {
        return UnitOfMeasure.valueOf(value.toUpperCase());
    }
}