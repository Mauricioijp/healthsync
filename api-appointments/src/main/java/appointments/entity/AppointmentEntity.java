package appointments.entity;

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
@Table(name = "appointments")
@Schema(description = "Appointments entity")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idapp")
    private Integer idapp;
    @Column(name = "patientid")
    private Integer patientid;
    @Column(name = "medicalrecordnumber")
    private String medicalrecordnumber;
    @Column(name = "nationalid")
    private String nationalid;
    @Column(name = "doctor")
    private String doctor;
    @Column(name = "date")
    private Date date;
    @Column(name = "state")
    private Integer state;
}
