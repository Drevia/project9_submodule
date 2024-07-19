package com.openclassrooms.medilabo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Data
public class PatientDto {

    private String firstName;
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private OffsetDateTime birthDate;
    private Gender gender;
    private String address;
    private String phoneNumber;
}
