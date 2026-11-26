package patients.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import patients.entity.PatientEntity;
import patients.repository.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    void testCreatePatient() {

    }

    @Test
    void testDeletePatient() {

    }

    @Test
    void testGetAllPatients() {

    }

    @Nested
    class testGetPatientById {

        @Test
        void shouldReturnPatientWhenIdExists() {
            Integer existingId = 1;
            PatientEntity patient = new PatientEntity();
            patient.setId(existingId);
            patient.setGivenName("Javier");
            patient.setFamilyName("Perez");
            patient.setMedicalRecordNumber("123456^^^Hospital^MR");

            when(patientRepository.findById(existingId)).thenReturn(java.util.Optional.of(patient));

            PatientEntity result = patientService.getPatientById(existingId);

            assertNotNull(result);
            assertEquals(existingId, result.getId());
            assertEquals("Javier", result.getGivenName());

            verify(patientRepository).findById(existingId);
        }

        @Test
        void shouldReturnNullWhenPatientNotFound() {
            Integer nonExistingId = 999;
            when(patientRepository.findById(nonExistingId)).thenReturn(java.util.Optional.empty());

            PatientEntity result = patientService.getPatientById(nonExistingId);

            assertNull(result);
            verify(patientRepository).findById(nonExistingId);
        }
    }

    @Test
    void testUpdatePatient() {

    }
}
