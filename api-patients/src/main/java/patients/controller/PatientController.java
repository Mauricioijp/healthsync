package patients.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import patients.entity.PatientEntity;
import patients.service.PatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(value="/patients", produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Patients", description = "Patients API")
@Log
public class PatientController {
    
    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Get all patients", description = "Retrieve a list of all patients")
    public List<PatientEntity> getAllPatients() {
        log.info("Fetching all patients");
        return patientService.getAllPatients();
    }

    @GetMapping("/{idPatient}")
    @Operation(summary = "Get patient by ID", description = "Retrieve a patient by their ID")
    public PatientEntity getPatientById(@PathVariable Integer idPatient) {
        return patientService.getPatientById(idPatient);
    }

    @PostMapping
    @Operation(summary = "Create a new patient", description = "Create a new patient record")
    public PatientEntity createPatient(@RequestBody PatientEntity patient) {
        log.info("Creating a new patient: " + patient);
        return patientService.createPatient(patient);
    }
    
    @PutMapping("/{idPatient}")
    @Operation(summary = "Update a patient", description = "Update an existing patient record")
    public PatientEntity updatePatient(@PathVariable Integer idPatient, @RequestBody PatientEntity patient) {
        patient.setId(idPatient);
        return patientService.updatePatient(patient);
    }
    
    @DeleteMapping("/{idPatient}")
    @Operation(summary = "Delete a patient", description = "Delete a patient record by ID")
    public PatientEntity deletePatient(@PathVariable Integer idPatient) {
        log.info("Deleting patient with ID: " + idPatient);
        return patientService.deletePatient(idPatient);
    }

    //@GetMapping("/search/{name}")
    //@Operation(summary = "Search patients by name", description = "Retrieve a list of patients whose names contain the specified string")
    //public List<PatientEntity> searchPatientsByName(@PathVariable String name) {
    //    log.info("Searching patients by name: " + name);
    //    return patientService.searchPatientsByName(name);
    //}
}
