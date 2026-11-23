package appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import appointments.entity.AppointmentEntity;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
    
}
