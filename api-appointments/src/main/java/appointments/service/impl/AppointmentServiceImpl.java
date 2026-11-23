package appointments.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import appointments.entity.AppointmentEntity;
import appointments.repository.AppointmentRepository;
import appointments.service.AppointmentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentEntity> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public AppointmentEntity getAppointmentById(Integer id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public AppointmentEntity createAppointment(AppointmentEntity appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public AppointmentEntity updateAppointment(Integer id, AppointmentEntity appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public AppointmentEntity deleteAppointment(Integer id) {
        AppointmentEntity appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null) {
            appointment.setState(0); // Assuming 0 represents the deleted state
            appointment = appointmentRepository.save(appointment);
        }
        return appointment;
    }
    
}
