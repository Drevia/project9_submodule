package com.openclassrooms.medilabofront.service;

import com.openclassrooms.medilabofront.client.medilaboResult.MedilaboResultClient;
import com.openclassrooms.medilabofront.client.medilabonote.MedilaboNoteGatewayClient;
import com.openclassrooms.medilabofront.client.medilabonote.model.PatientNote;
import com.openclassrooms.medilabofront.client.medilabonote.model.PatientNoteDto;
import com.openclassrooms.medilabofront.client.medilaboservice.MedilaboGatewayClient;
import com.openclassrooms.medilabofront.client.medilaboservice.model.Patient;
import com.openclassrooms.medilabofront.mapper.PatientMapper;
import com.openclassrooms.medilabofront.model.PatientWithNoteDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedilaboFrontService {

    @Autowired
    private MedilaboGatewayClient medilaboGatewayClient;

    @Autowired
    private MedilaboNoteGatewayClient medilaboNoteGatewayClient;

    @Autowired
    private MedilaboResultClient medilaboResultClient;

    @Autowired
    private PatientMapper mapper;

    public List<Patient> medilaboPatientFindAll() {

        return medilaboGatewayClient.findAllPatient();
    }

    public List<PatientNote> medilaboPatientNoteFindAll() {

        return medilaboNoteGatewayClient.findAllPatientNote();
    }

    public List<PatientNote> medilaboPatientNoteFindAllByPatientId(String id) {
        return medilaboNoteGatewayClient.findAllPatientNoteByPatientId(id);
    }

    public Patient medilaboPatientFindById(Long id) {
        return medilaboGatewayClient.findPatientById(id);
    }

    public void medilaboPatientUpdatePatient(Long patientId, String address, String phoneNumber) {
        Patient patient = medilaboPatientFindById(patientId);
        patient.setAddress(address);
        patient.setPhoneNumber(phoneNumber);
        medilaboGatewayClient.updatePatient(mapper.patientToDto(patient), patientId);
    }

    public List<PatientWithNoteDto> buildPatientWithNote(List<Patient> patients, List<PatientNote> notes) {

        return patients.stream().map(patient -> {
            PatientWithNoteDto dto = new PatientWithNoteDto();
            dto.setId(patient.getId());
            dto.setFirstName(patient.getFirstName());
            dto.setLastName(patient.getLastName());
            dto.setBirthDate(patient.getBirthDate());
            dto.setGender(patient.getGender());
            dto.setAddress(patient.getAddress());
            dto.setPhoneNumber(patient.getPhoneNumber());
            dto.setNote(extractNoteByPatientId(patient.getId(), notes));
            return dto;
        }).toList();
    }

    private String extractNoteByPatientId(Long id, List<PatientNote> patientNotes) {

        if (CollectionUtils.isEmpty(patientNotes)){
            return StringUtils.EMPTY;
        } else{
            return patientNotes.stream().filter(patientNote -> id.toString()
                            .equalsIgnoreCase(patientNote.getPatientId())).map(PatientNote::getNote)
                    .collect(Collectors.joining(" / "));
        }
    }

    public void addPatientNote(Long patientId, String newNote) {
        PatientNoteDto patientNote = new PatientNoteDto();
        patientNote.setNote(newNote);
        patientNote.setPatientId(patientId.toString());
        patientNote.setPatientName(medilaboGatewayClient.findPatientById(patientId).getLastName());

        medilaboNoteGatewayClient.createPatientNote(patientNote);
    }

    public String getDangerResult(String id) {
        return medilaboResultClient.getMedilaboResult(id);
    }
}
