package com.openclassrooms.medilabofront.mapper;

import com.openclassrooms.medilabofront.client.medilaboservice.model.Patient;
import com.openclassrooms.medilabofront.client.medilaboservice.model.PatientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDto patientToDto(Patient patient);
}
