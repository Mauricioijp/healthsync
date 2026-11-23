package patients.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import patients.entity.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {
    //public Page<PatientEntity> findByFamilyNameContainingIgnoreCase(String name);
}
