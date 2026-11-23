package frontend.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import frontend.entity.AppointmentEntity;
import frontend.entity.PatientEntity;
import frontend.service.AppointmentService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AppointmentController {

    private static final Logger log = LoggerFactory.getLogger(AppointmentController.class);
    
    private final AppointmentService appointmentService;


    @GetMapping("/appointmentsMaintenance")
    public ModelAndView appointmentsMaintenance(HttpSession session) {
        String token = (String) session.getAttribute("token");
        ModelAndView mv =
            new ModelAndView("appointments/appointmentsMaintenance");

        List<AppointmentEntity> appointments =
            appointmentService.listAppointments(token);

        log.info("Citas obtenidas: " + appointments);

        mv.addObject("appointmentsList", appointments);

        return mv;
    }

    @GetMapping("/appointments/newAppointmentForm")
    public ModelAndView newAppointmentForm() {

        ModelAndView mv = new ModelAndView(
                "appointments/newAppointmentForm"
        );

        mv.addObject("appointment", new AppointmentEntity());

        return mv;
    }
}
