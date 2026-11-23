package frontend.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import frontend.entity.PatientEntity;
import frontend.service.PatientService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import tools.jackson.databind.ObjectMapper;

@Controller
@Log
@AllArgsConstructor
public class PatientController {

    private static final Logger log = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;


    
    @GetMapping("/patientsMaintenance")
    public ModelAndView patientsMaintenance(HttpSession session) {

        ModelAndView mv = null;
        String token = (String) session.getAttribute("token");
        System.out.println(token);
        if (token != null) {
            mv =
                new ModelAndView("patients/patientsMaintenance");

            List<PatientEntity> usuarios =
                patientService.listPatients(token);

            log.info("Usuarios obtenidos: " + usuarios);

            mv.addObject("listaUsuarios", usuarios);

        } else {
            mv = new ModelAndView("error/401");
        }
        return mv;
        
    }



    @GetMapping("/patients/newPatientForm")
    public ModelAndView newPatientForm() {

        ModelAndView mv = new ModelAndView(
                "patients/newPatientForm"
        );

        mv.addObject("patient", new PatientEntity());

        return mv;
    }

    @PostMapping("/savePatient")
    public ModelAndView savePatient(
            @ModelAttribute("patient") PatientEntity patient, HttpSession session) {

        String token = (String) session.getAttribute("token");
        System.out.println(token);
        PatientEntity patientData =
                new ObjectMapper().convertValue(patient, PatientEntity.class);

        patientService.createPatient(patientData, token);

        log.info("Patient data sent to service: " + patientData);

        return new ModelAndView("redirect:/patientsMaintenance");
    }

    @GetMapping("/patients/edit/{id}")
    public ModelAndView editPatient(
            @PathVariable Integer id, HttpSession session) {
        String token = (String) session.getAttribute("token");
        PatientEntity existingPatient = patientService.getPatientById(id, token);

        ModelAndView mv = new ModelAndView(
                "patients/newPatientForm"
        );
        
        mv.addObject("patient", existingPatient);
        //mv.addObject("editMode", true);

        return mv;
    }

    @PostMapping("/patients/delete/{id}")
    public String deletePatient(
            @PathVariable Integer id, HttpSession session) {

        String token = (String) session.getAttribute("token");
        log.info("Deleting patient id: {}", id);

        patientService.deletePatient(id, token);

        return "redirect:/patientsMaintenance";
    }
}
