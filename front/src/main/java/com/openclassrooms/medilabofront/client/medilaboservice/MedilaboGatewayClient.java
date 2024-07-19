package com.openclassrooms.medilabofront.client.medilaboservice;

import com.openclassrooms.medilabofront.client.medilaboservice.model.Patient;
import com.openclassrooms.medilabofront.client.medilaboservice.model.PatientDto;
import com.openclassrooms.medilabofront.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8090/api", name = "MedilaboPatient", configuration = FeignConfiguration.class)
public interface MedilaboGatewayClient {

    @GetMapping("/patient")
    List<Patient> findAllPatient();

    @GetMapping("/patient/{id}")
    Patient findPatientById(@PathVariable Long id);

    @PatchMapping("/patient/{id}")
    Patient updatePatient(PatientDto patient, @PathVariable Long id);
}
