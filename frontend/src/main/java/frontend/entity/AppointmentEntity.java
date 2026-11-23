package frontend.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEntity {
    private Integer idapp;
    private Integer patientid;
    private String medicalrecordnumber;
    private String nationalid;
    private String doctor;
    private LocalDate date;
    private String state;
}
