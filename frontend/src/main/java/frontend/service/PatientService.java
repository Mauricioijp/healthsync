package frontend.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import frontend.entity.PatientEntity;
import frontend.repository.PatientRepository;
import lombok.extern.java.Log;

@Service
@Log
public class PatientService {
    
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public List<PatientEntity> listPatients(String token) {
        return patientRepository.listPatients(token);
    }

    public PatientEntity getPatientById(Integer id, String token) {
        return patientRepository.getPatientById(id, token);
    }

    public void createPatient(PatientEntity patientData, String token) {
        patientRepository.createPatient(patientData, token);
    }
    
    public PatientEntity updatePatient(PatientEntity patient, String token) {
        return patientRepository.updatePatient(patient, token);
    }
    
    public void deletePatient(Integer id, String token) {
        patientRepository.deletePatient(id, token);
    }
}