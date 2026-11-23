package frontend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import frontend.service.UserService;
import lombok.extern.java.Log;

@Controller
@Log
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/usersMaintenance")
    public ModelAndView usersMaintenance() {

        ModelAndView mv =
            new ModelAndView("users/usersMaintenance");

        List<Map<String,Object>> usersList =
            userService.listUsers();

        log.info("Usuarios obtenidos: " + usersList);

        mv.addObject("usersList", usersList);

        return mv;
    }
}
