package frontend.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import frontend.entity.LoginDTO;
import frontend.entity.UserEntity;
import lombok.extern.java.Log;

@Repository
@Log
public class UserRepository {

    @Value("${uri.rest.api.security}")
    private String uriRestApiSecurity;

    @Value("${uri.rest.api.users}")
    private String uriRestApiUsers;

    private final RestTemplate restTemplate;

    public UserRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

        private <T> HttpEntity<T> authEntity(String token, T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        return new HttpEntity<>(body, headers);
    }

    public UserEntity validarUsuario(String user, String token) {
        log.info("Mensaje del repository del front");
        System.out.println("Mensaje del repository del front");
        ResponseEntity<UserEntity> response = restTemplate.exchange(
                uriRestApiUsers + "/" + user, HttpMethod.GET, authEntity(token, null), UserEntity.class);
        System.out.println("Usuario obtenido: " + response.getBody());
        return response.getBody();
    }

    public String obtenerToken(String user, String password) {
        LoginDTO credenciales = new LoginDTO(user, password);
        System.out.println("Mensaje del obtenerToken del front");
        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(uriRestApiSecurity, credenciales, Void.class);
            System.out.println("Token obtenido: " + response.getHeaders().getFirst("Authorization"));
            return response.getHeaders().getFirst("Authorization");
        } catch (HttpClientErrorException.Unauthorized e) {
            return null;
        } catch (HttpClientErrorException.Forbidden e) {
            return null;
        }
    }


    public List<Map<String, Object>> listUsers() {
        String url = "http://localhost:8000/users";
        try {
            ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
    public Map<String, Object> createUser(Map<String, Object> userData) {
        String url = "http://localhost:8000/users";
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, userData, Map.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
    
}
