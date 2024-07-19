package com.openclassrooms.medilabofront.client.medilabonote.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientNote {

    private String id;
    private String patientId;
    private String patientName;
    private String note;
}
