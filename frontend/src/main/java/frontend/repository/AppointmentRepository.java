package frontend.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import frontend.entity.AppointmentEntity;
import lombok.extern.java.Log;

@Repository
@Log
public class AppointmentRepository {

    @Value("${uri.rest.api.appointments}")
    String uriRestApiAppointments;
    
    private final RestTemplate restTemplate;

    public AppointmentRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private <T> HttpEntity<T> authEntity(String token, T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        return new HttpEntity<>(body, headers);
    }

    public List<AppointmentEntity> listAppointments(String token) {

        ResponseEntity<AppointmentEntity[]> response =
                restTemplate.exchange(
                        uriRestApiAppointments, HttpMethod.GET, authEntity(token, null),
                        AppointmentEntity[].class);
        return Arrays.asList(response.getBody());
    }
}

/*HttpHeaders headers = new HttpHeaders();

headers.setBearerAuth(token);
// Equivale a:
// Authorization: Bearer eyJhbGc...

HttpEntity<Void> entity = new HttpEntity<>(headers);

ResponseEntity<List> response =
        restTemplate.exchange(
                "http://localhost:8000/appointments",
                HttpMethod.GET,
                entity,
                List.class); */
