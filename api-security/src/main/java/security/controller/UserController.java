package security.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import security.entity.UserEntity;
import security.service.UserService;

@RestController
@RequestMapping(value="/users", produces=MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Log
public class UserController {
        private final UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        log.info("Toda la lista de usuarios");
        return userService.listUsers();
    }
    
    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable String id) {
        log.info("Fetching user with ID: " + id);
        System.out.println("Fetching user with ID: " + id);
        return userService.getUser(id);
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        System.out.println("Mensaje del obtenerToken del front");
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable String id, UserEntity user) {
        user.setUser(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public UserEntity deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
