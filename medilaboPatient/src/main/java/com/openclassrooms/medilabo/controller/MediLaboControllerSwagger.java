package com.openclassrooms.medilabo.controller;

import com.openclassrooms.medilabo.exception.PatientNotFoundException;
import com.openclassrooms.medilabo.model.Patient;
import com.openclassrooms.medilabo.model.PatientDto;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@RequestMapping("/api")
public interface MediLaboControllerSwagger {


    @GetMapping("/patient")
    @Operation(summary = "List Patient object", description = "This operation List all Patient entities")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Patient.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "404", description = "Entity not found."),
            @APIResponse(responseCode = "401", description = "Unauthorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "500", description = "Internal server error.")
    })
    ResponseEntity<List<Patient>> getAllPatient();

    @GetMapping("/patient/{id}")
    @Operation(summary = "Retrieve a Patient by ID", description = "This operation retrieves a Patient entities")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Patient.class))),
            @APIResponse(responseCode = "404", description = "Entity not found."),
            @APIResponse(responseCode = "401", description = "Unauthorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "500", description = "Internal server error.")
    })
    ResponseEntity<?> getPatientById(@Parameter(description = "Identifier of the Patient", required = true) @PathVariable String id) throws PatientNotFoundException;

    @PostMapping("/patient")
    @Operation(summary = "Creates a Patient", description = "This operation creates a Patient entity")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = Patient.class))),
            @APIResponse(responseCode = "400", description = "Bad Request."),
            @APIResponse(responseCode = "401", description = "Unauthorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "500", description = "Internal server error.")
    })
    ResponseEntity<Patient> createPatient(@RequestBody(content = @Content(schema = @Schema(implementation = PatientDto.class)), required = true, description = "The Patient to be created") PatientDto patientDto);

    @PatchMapping("/patient/{id}")
    @Operation(summary = "Update a Patient", description = "This operation update a Patient entity")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = Patient.class))),
            @APIResponse(responseCode = "400", description = "Bad Request."),
            @APIResponse(responseCode = "401", description = "Unauthorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "500", description = "Internal server error.")
    })
    ResponseEntity<?> updatePatient(@RequestBody(content = @Content(schema = @Schema(implementation = PatientDto.class)), required = true, description = "The Patient to be updated") PatientDto patientDto,
                                    @Parameter(description = "Identifier of the Patient", required = true) @PathVariable String id) throws PatientNotFoundException;

    @DeleteMapping("/patient/{id}")
    @Operation(summary = "Delete a Patient", description = "This operation delete a Patient entity")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = Patient.class))),
            @APIResponse(responseCode = "400", description = "Bad Request."),
            @APIResponse(responseCode = "401", description = "Unauthorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "500", description = "Internal server error.")
    })
    void deletePatient(@Parameter(description = "Identifier of the Patient", required = true) @PathVariable String id);
}
