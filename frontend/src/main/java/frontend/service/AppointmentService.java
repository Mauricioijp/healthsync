package frontend.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import frontend.entity.AppointmentEntity;
import frontend.repository.AppointmentRepository;
import lombok.extern.java.Log;

@Service
@Log
public class AppointmentService {
    
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<AppointmentEntity> listAppointments(String token) {
        return appointmentRepository.listAppointments(token);
    }
}
