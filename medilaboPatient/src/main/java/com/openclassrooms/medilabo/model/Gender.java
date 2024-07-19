package com.openclassrooms.medilabo.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Gender {

    MALE("M"), FEMALE("F");

    private final String value;

    Gender(String value) {
        this.value = value;
    }
}
