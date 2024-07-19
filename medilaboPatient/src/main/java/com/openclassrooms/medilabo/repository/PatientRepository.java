package com.openclassrooms.medilabo.repository;

import com.openclassrooms.medilabo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    Patient findPatientByLastNameAndFirstName(String lastName, String firstName);
}
