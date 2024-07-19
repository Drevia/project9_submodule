package com.openclassrooms.medilabofront.model;

import com.openclassrooms.medilabofront.client.medilaboservice.model.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class PatientWithNoteDto {

    private Long id;
    private String firstName;
    private String lastName;
    private OffsetDateTime birthDate;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private String note;
    private String dangerResult;
}
