package com.openclassrooms.medilabo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.medilabo.model.Gender;
import com.openclassrooms.medilabo.model.Patient;
import com.openclassrooms.medilabo.model.PatientDto;
import com.openclassrooms.medilabo.repository.PatientRepository;
import com.openclassrooms.medilabo.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MedilaboControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private JwtService jwtService;

    @BeforeEach
    void initMock() {
        when(jwtService.extractUsername(any())).thenReturn("user");
    }

    @Test
    void createPatientOk() throws Exception {

        PatientDto patient = buildPatient();
        String json = mapper.writeValueAsString(patient);

        mockMvc.perform(post("/api/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .header("Authorization", "Bearer jwt-test-token")).andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void retrieveAllPatientOk() throws Exception {

        Patient patient = new Patient();
        repository.save(patient);

        mockMvc.perform(get("/api/patient")
                        .header("Authorization", "Bearer jwt-test-token")
                        .with(csrf())).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.*", hasSize(1)));
    }

    @Test
    void retrievePatientByIdOk() throws Exception {
        Patient patient = new Patient();
        repository.save(patient);

        mockMvc.perform(get("/api/patient/1").header("Authorization", "Bearer jwt-test-token")
                .with(csrf())).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void updatePatientOk() throws Exception {
        Patient patient = new Patient();
        patient.setAddress("a");
        patient.setFirstName("b");
        patient.setLastName("c");
        patient.setGender(Gender.MALE);
        patient.setPhoneNumber("d");
        patient.setBirthDate(OffsetDateTime.now());
        repository.save(patient);

        PatientDto newPatient = buildPatient();
        String json = mapper.writeValueAsString(newPatient);

        mockMvc.perform(patch("/api/patient/1").header("Authorization", "Bearer jwt-test-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf())).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    private PatientDto buildPatient() {
        PatientDto patient = new PatientDto();
        patient.setAddress("address");
        patient.setFirstName("firstName");
        patient.setLastName("lastName");
        patient.setGender(Gender.MALE);
        patient.setPhoneNumber("phone");
        patient.setBirthDate(OffsetDateTime.now());
        return patient;
    }

}
