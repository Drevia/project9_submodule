package com.openclassrooms.medilabo.controller;

import com.openclassrooms.medilabo.exception.PatientNotFoundException;
import com.openclassrooms.medilabo.model.Patient;
import com.openclassrooms.medilabo.model.PatientDto;
import com.openclassrooms.medilabo.service.MediLaboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MediLaboController implements MediLaboControllerSwagger {

    @Autowired
    private MediLaboService mediLaboService;

    private final Logger logger = LoggerFactory.getLogger(MediLaboController.class);

    public ResponseEntity<List<Patient>> getAllPatient() {
        logger.info("find all patient");
        return ResponseEntity.ok(mediLaboService.findAllPatient());
    }

    public ResponseEntity<?> getPatientById(String id) {
        try {
            return ResponseEntity.ok(mediLaboService.findPatientById(id));
        } catch (PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    public ResponseEntity<Patient> createPatient(@RequestBody PatientDto patientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mediLaboService.savePatient(patientDto));
    }

    public ResponseEntity<?> updatePatient(@RequestBody PatientDto patientDto, String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mediLaboService.updatePatient(patientDto, id));
        }catch (PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public void deletePatient(String id) {
        logger.info("delete patient with id: {}", id);
        mediLaboService.deletePatient(id);
    }
}
