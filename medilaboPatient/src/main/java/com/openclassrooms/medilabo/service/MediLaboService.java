package com.openclassrooms.medilabo.service;

import com.openclassrooms.medilabo.exception.PatientNotFoundException;
import com.openclassrooms.medilabo.mapper.PatientMapper;
import com.openclassrooms.medilabo.model.Patient;
import com.openclassrooms.medilabo.model.PatientDto;
import com.openclassrooms.medilabo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediLaboService {

    @Autowired
    private PatientRepository repository;

    @Autowired
    private PatientMapper patientMapper;

    public List<Patient> findAllPatient() {
        return repository.findAll();
    }

    public Patient findPatientById(String id) throws PatientNotFoundException {
        return repository.findById(id).orElseThrow(() ->
                new PatientNotFoundException("Patient with id "+ id +" not found"));
    }

    public Patient savePatient(PatientDto patientDto){
        Patient patient = patientMapper.patientDtoToPatient(patientDto);

        return repository.save(patient);
    }

    public Patient updatePatient(PatientDto patientDto, String id) throws PatientNotFoundException {
        Patient patient = findPatientById(id);
        patient.setAddress(patientDto.getAddress());
        patient.setGender(patientDto.getGender());
        patient.setBirthDate(patientDto.getBirthDate());
        patient.setLastName(patientDto.getLastName());
        patient.setFirstName(patientDto.getFirstName());
        patient.setPhoneNumber(patientDto.getPhoneNumber());

        return repository.save(patient);
    }

    public void deletePatient(String id) {
        repository.deleteById(id);
    }
}
