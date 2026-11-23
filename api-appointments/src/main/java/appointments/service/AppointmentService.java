package appointments.service;

import java.util.List;

import appointments.entity.AppointmentEntity;

public interface AppointmentService {
    
    public List<AppointmentEntity> getAllAppointments();
    public AppointmentEntity getAppointmentById(Integer id);
    public AppointmentEntity createAppointment(AppointmentEntity appointment);
    public AppointmentEntity updateAppointment(Integer id, AppointmentEntity appointment);
    public AppointmentEntity deleteAppointment(Integer id);
}
