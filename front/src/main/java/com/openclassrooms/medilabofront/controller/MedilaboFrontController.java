package com.openclassrooms.medilabofront.controller;

import com.openclassrooms.medilabofront.client.medilabonote.model.PatientNote;
import com.openclassrooms.medilabofront.client.medilaboservice.model.Patient;
import com.openclassrooms.medilabofront.model.PatientWithNoteDto;
import com.openclassrooms.medilabofront.service.MedilaboFrontService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MedilaboFrontController {

    @Autowired
    private MedilaboFrontService frontService;

    @GetMapping("/login")
    public String homePage() {
        return "login";
    }

    @GetMapping("/medilabo")
    public String showMedilaboPage() {
        return "medilabo";
    }

    @GetMapping("/medilabo/patient/list")
    public String patientPage(Model model) {

        List<Patient> patients = frontService.medilaboPatientFindAll();
        List<PatientNote> notes = frontService.medilaboPatientNoteFindAll();

        List<PatientWithNoteDto> patientWithNote = frontService.buildPatientWithNote(patients, notes);

        model.addAttribute("patients", patientWithNote);
        return "patient/list";
    }

    @GetMapping("/medilabo/patient/{id}")
    public String getPatientDetails(@PathVariable Long id, Model model) {
        Patient patient = frontService.medilaboPatientFindById(id);

        List<PatientNote> notes = frontService.medilaboPatientNoteFindAllByPatientId(patient.getId().toString());

        List<PatientWithNoteDto> patientWithNote = frontService.buildPatientWithNote(List.of(patient), notes);

        String dangerResult = frontService.getDangerResult(id.toString());

        model.addAttribute("patient", patientWithNote.get(0));
        model.addAttribute("allNotes", notes);
        model.addAttribute("result", dangerResult);
        return "patient/patientFile";
    }

    @PostMapping("/medilabo/updatePatient")
    public String updatePatient(@RequestParam("id") Long patientId, @RequestParam("address") String address,
                                @RequestParam("phoneNumber") String phoneNumber,
                                @RequestParam(required = false)String newNote,
                                RedirectAttributes redirectAttributes) {
        if (StringUtils.isNotEmpty(newNote)) {
            frontService.addPatientNote(patientId, newNote);
        }


        frontService.medilaboPatientUpdatePatient(patientId, address, phoneNumber);

        redirectAttributes.addFlashAttribute("success",
                "Les informations du patient ont été mise à jour");
        return "redirect:/medilabo/patient/" + patientId;
    }
}
