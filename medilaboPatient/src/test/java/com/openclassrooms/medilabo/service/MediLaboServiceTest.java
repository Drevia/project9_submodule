package com.openclassrooms.medilabo.service;

import com.openclassrooms.medilabo.mapper.PatientMapper;
import com.openclassrooms.medilabo.model.Gender;
import com.openclassrooms.medilabo.model.Patient;
import com.openclassrooms.medilabo.model.PatientDto;
import com.openclassrooms.medilabo.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MediLaboServiceTest {

    @Mock
    PatientRepository repository;

    @InjectMocks
    MediLaboService service;

    @Mock
    PatientMapper mapper;

    @Test
    void findAllPatientOk() {
        Patient patient = new Patient();
        when(repository.findAll()).thenReturn(List.of(patient));

        List<Patient> patientList = assertDoesNotThrow(() -> service.findAllPatient());
        assertEquals(1, patientList.size());
    }

    @Test
    void findPatientByIdOk() {
        Patient patient = new Patient();
        when(repository.findById(any())).thenReturn(Optional.of(patient));
        assertDoesNotThrow(() -> service.findPatientById("id"));
    }

    @Test
    void savePatientOk() {
        PatientDto dto = new PatientDto();
        when(mapper.patientDtoToPatient(dto)).thenReturn(new Patient());
        when(repository.save(any())).thenReturn(new Patient());
        Patient patient = assertDoesNotThrow(() -> service.savePatient(dto));
        assertNotNull(patient.getLastName());
    }

    @Test
    void updatePatientOk() {
        PatientDto dto = new PatientDto();
        dto.setAddress("newAddress");
        dto.setGender(Gender.MALE);
        dto.setBirthDate(OffsetDateTime.now());
        dto.setPhoneNumber("newPhoneNumber");
        dto.setLastName("newLastName");
        dto.setFirstName("newFirstName");

        Patient patient = new Patient();

        when(repository.findById(any())).thenReturn(Optional.of(patient));
        when(repository.save(any())).thenReturn(patient);
        assertDoesNotThrow(() -> service.updatePatient(dto, "id"));
        assertEquals(dto.getAddress(), patient.getAddress());
        assertEquals(dto.getFirstName(), patient.getFirstName());
        assertEquals(dto.getLastName(), patient.getLastName());
        assertEquals(dto.getPhoneNumber(), patient.getPhoneNumber());
    }

    @Test
    void deletePatientOk() {

        doNothing().when(repository).deleteById(any());
        assertDoesNotThrow(() -> service.deletePatient("id"));
    }
}
