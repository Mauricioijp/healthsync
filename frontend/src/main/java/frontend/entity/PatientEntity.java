package frontend.entity;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity {
    private Integer id;
    private String medicalRecordNumber;
    private String nationalId;
    private String patientIdentifier;
    private String familyName;
    private String givenName;
    private String sex;
    private LocalDate birthDate; //LocalDate
    private String phone;
}
