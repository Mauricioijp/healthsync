package patients.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import patients.entity.PatientEntity;

public interface PatientService {
    public List<PatientEntity> getAllPatients();
    public PatientEntity getPatientById(Integer id);
    public PatientEntity createPatient(PatientEntity patient);
    public PatientEntity updatePatient(PatientEntity patient);
    public PatientEntity deletePatient(Integer id);
    //public Page<PatientEntity> searchPatientsByName(String name);
}
