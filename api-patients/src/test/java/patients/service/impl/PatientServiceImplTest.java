package patients.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

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

    @Nested
    class CreatePatientTests {

        @Test
        void shouldCreatePatient() {

            // Arrange
            PatientEntity patient = new PatientEntity();
            patient.setGivenName("John");
            patient.setFamilyName("Smith");

            when(patientRepository.save(patient))
                .thenReturn(patient);

            // Act
            PatientEntity result =
                patientService.createPatient(patient);

            // Assert
            assertNotNull(result);
            assertEquals("John", result.getGivenName());
            assertEquals("Smith", result.getFamilyName());

            verify(patientRepository, times(1)).save(patient);
        }
    }

    @Nested
    class DeletePatientTests {

        @Test
        void shouldDeletePatient() {

            // Arrange
            Integer patientId = 1;

            PatientEntity patient = new PatientEntity();
            patient.setId(patientId);
            patient.setGivenName("John");

            when(patientRepository.findById(patientId))
                .thenReturn(Optional.of(patient));

            // Act
            PatientEntity result =
                patientService.deletePatient(patientId);

            // Assert
            assertNotNull(result);
            assertEquals(patientId, result.getId());

            verify(patientRepository, times(1))
                .findById(patientId);

            verify(patientRepository, times(1))
                .delete(patient);
        }
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

    @Nested
    class UpdatePatientTests {

        @Test
        void shouldUpdatePatient() {

            // Arrange
            Integer patientId = 1;

            PatientEntity existingPatient = new PatientEntity();
            existingPatient.setId(patientId);
            existingPatient.setGivenName("John");
            existingPatient.setFamilyName("Smith");

            PatientEntity updatedPatient = new PatientEntity();
            updatedPatient.setId(patientId);
            updatedPatient.setGivenName("Jonathan");
            updatedPatient.setFamilyName("Doe");

            when(patientRepository.findById(patientId))
                .thenReturn(Optional.of(existingPatient));

            when(patientRepository.save(existingPatient))
                .thenReturn(existingPatient);

            // Act
            PatientEntity result =
                patientService.updatePatient(updatedPatient);

            // Assert
            assertNotNull(result);
            assertEquals(patientId, result.getId());
            assertEquals("Jonathan", result.getGivenName());
            assertEquals("Doe", result.getFamilyName());

            verify(patientRepository, times(1))
                .findById(patientId);

            verify(patientRepository, times(1))
                .save(existingPatient);
        }

        @Test
        void shouldThrowExceptionWhenPatientDoesNotExist() {

            // Arrange
            Integer patientId = 999;

            PatientEntity patient = new PatientEntity();
            patient.setId(patientId);

            when(patientRepository.findById(patientId))
                .thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(RuntimeException.class, () ->
                patientService.updatePatient(patient)
            );

            verify(patientRepository, never()).save(any());
        }
    }
}
