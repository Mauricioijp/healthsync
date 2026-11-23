package patients.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patients.entity.PatientEntity;
import patients.repository.PatientRepository;
import patients.service.PatientService;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public PatientEntity getPatientById(Integer id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public PatientEntity createPatient(PatientEntity patient) {
        return patientRepository.save(patient);
    }

    @Override
    public PatientEntity updatePatient(PatientEntity patient) {
        PatientEntity existingPatient = patientRepository.findById(patient.getId())
            .orElseThrow(() -> new RuntimeException("Patient not found"));

        existingPatient.setMedicalRecordNumber(patient.getMedicalRecordNumber());
        existingPatient.setNationalId(patient.getNationalId());
        existingPatient.setPatientIdentifier(patient.getPatientIdentifier());
        existingPatient.setFamilyName(patient.getFamilyName());
        existingPatient.setGivenName(patient.getGivenName());
        existingPatient.setSex(patient.getSex());
        existingPatient.setBirthDate(patient.getBirthDate());
        existingPatient.setPhone(patient.getPhone());

        return patientRepository.save(existingPatient);
        
        //return patientRepository.save(patient);
    }

    @Override
    public PatientEntity deletePatient(Integer id) {
        PatientEntity patient = patientRepository.findById(id).orElse(null);
        patientRepository.delete(patient);
        return patient;
    }

    //@Override
    //public Page<PatientEntity> searchPatientsByName(String name) {
    //    return patientRepository.findByFamilyNameContainingIgnoreCase(name);
    //}
    
}
