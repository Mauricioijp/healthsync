package frontend.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import frontend.entity.UserEntity;
import frontend.repository.UserRepository;
import lombok.extern.java.Log;

@Service
@Log
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserEntity validarUsuario(String user, String token) {
        log.info("Mensaje del service del front");
        System.out.println("Mensaje del service del front");
        return userRepository.validarUsuario(user, token);
    }

    public String obtenerToken(String user, String password) {
        return userRepository.obtenerToken(user, password);
    }




//    public Map<String, Object> validarUsuario(String username, String password) {
//
//        Map<String, Object> user = userRepository.validarUsuario(username, password);

//        if (user != null && user.get("password").equals(password)) {
//            return user;
//        } else {
//            return null;
//        }
//    }

    public List<Map<String, Object>> listUsers() {
        return userRepository.listUsers();
    }
}
