package patients.entity;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "patients")
@Schema(name = "Patients", description = "Patients entity")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "medicalrecordnumber")
    private String medicalRecordNumber;
    @Column(name = "nationalid")
    private String nationalId;
    @Column(name = "patientidentifier")
    private String patientIdentifier;
    @Column(name = "familyname")
    private String familyName;
    @Column(name = "givenname")
    private String givenName;
    @Column(name = "sex") //CAMBIAR A UN ENUM SERIA MEJOR
    private String sex;
    @Column(name = "birthdate")
    private Date birthDate;
    @Column(name = "phone")
    private String phone;

}
