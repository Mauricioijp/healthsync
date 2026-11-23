package appointments.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import appointments.entity.AppointmentEntity;
import appointments.service.AppointmentService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value="/appointments", produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AppointmentController {
    
    private final AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentEntity> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
    
    @GetMapping("/{idapp}")
    public AppointmentEntity getAppointmentById(@PathVariable Integer idapp) {
        return appointmentService.getAppointmentById(idapp);
    }

    @PostMapping
    public AppointmentEntity createAppointment(@RequestBody AppointmentEntity appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @PutMapping("/{idapp}")
    public AppointmentEntity updateAppointment(@PathVariable Integer idapp, @RequestBody AppointmentEntity appointment) {
        appointment.setIdapp(idapp);
        return appointmentService.updateAppointment(idapp, appointment);
    }

    @DeleteMapping("/{idapp}")
    public AppointmentEntity deleteAppointment(@PathVariable Integer idapp) {
        return appointmentService.deleteAppointment(idapp);
    }
}