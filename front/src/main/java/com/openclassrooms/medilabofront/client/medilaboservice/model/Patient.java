package com.openclassrooms.medilabofront.client.medilaboservice.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Data
public class Patient {


    private Long id;
    private String firstName;
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private OffsetDateTime birthDate;
    private Gender gender;
    private String address;
    private String phoneNumber;
}
