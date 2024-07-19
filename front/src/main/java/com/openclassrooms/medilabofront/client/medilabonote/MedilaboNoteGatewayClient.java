package com.openclassrooms.medilabofront.client.medilabonote;

import com.openclassrooms.medilabofront.client.medilabonote.model.PatientNote;
import com.openclassrooms.medilabofront.client.medilabonote.model.PatientNoteDto;
import com.openclassrooms.medilabofront.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8090/api", name = "MedilaboNote", configuration = FeignConfiguration.class)
public interface MedilaboNoteGatewayClient {

    @GetMapping("/note")
    List<PatientNote> findAllPatientNote();

    @GetMapping("/note/{id}")
    List<PatientNote> findAllPatientNoteByPatientId(@PathVariable String id);

    @PostMapping("/note")
    PatientNote createPatientNote(PatientNoteDto patientNote);
}
