package frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import frontend.entity.UserEntity;
import frontend.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Log
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String login(HttpSession session) {
        if (session.getAttribute("token") != null) {
            return "redirect:/index";
        }
        else {
            return "login";
        }
    }

    @PostMapping("/actionLogin")
    public ModelAndView actionLoginCliente(String user, String password, HttpSession session) {
        ModelAndView mv = new ModelAndView("login");
        String token = userService.obtenerToken(user, password);
        System.out.println(token);
        if (token != null) {
            
            UserEntity usuario = userService.validarUsuario(user, token);
        
            session.setAttribute("token", token);
            session.setAttribute("user", user);
            session.setAttribute("role", usuario.getRole().toUpperCase());
            System.out.println("Rol del usuario: " + session.getAttribute("role"));
            mv.setViewName("redirect:/index");
            //mv.setViewName("index");
        } else {
            mv.addObject("msgError", "Usuario y/o contraseña incorrectos");
        }
        return mv;
    }


    @RequestMapping("/index")
    public ModelAndView index(String user, String password, HttpSession session) {
        ModelAndView mv = null;
        String token = (String) session.getAttribute("token");
        System.out.println("Token en index: " + token);
        String role = (String) session.getAttribute("role");
        if (token != null) {
            switch (role) {
                case "ADMIN":
                    mv = new ModelAndView("index");
                    mv.setViewName("index");
                    break;
                case "DOCTOR":
                    mv = new ModelAndView("indexDoctor");
                    mv.setViewName("indexDoctor");
                    break;
                case "PATIENT":
                    mv = new ModelAndView("indexPatient");
                    mv.setViewName("indexPatient");
                    break;
                default:
                    mv = new ModelAndView("error/401");
                    break;
            }

            
        } else {
            mv = new ModelAndView("error/401");
        }
        return mv;
        

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
}
