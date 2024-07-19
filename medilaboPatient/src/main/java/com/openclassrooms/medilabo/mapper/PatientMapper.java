package com.openclassrooms.medilabo.mapper;

import com.openclassrooms.medilabo.model.Patient;
import com.openclassrooms.medilabo.model.PatientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient patientDtoToPatient(PatientDto patientDto);
}
