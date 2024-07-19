package com.openclassrooms.medilabo.exception;

public class PatientNotFoundException extends Exception{

    public PatientNotFoundException() {}
    public PatientNotFoundException(String message) {
        super(message);
    }
}
