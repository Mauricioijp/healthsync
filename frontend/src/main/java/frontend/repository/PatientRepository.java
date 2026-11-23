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

import frontend.entity.PatientEntity;
import lombok.extern.java.Log;

@Repository
@Log
public class PatientRepository {

    @Value("${uri.rest.api.patients}")
    String uriRestApiPatients;

    private final RestTemplate restTemplate;

    public PatientRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private <T> HttpEntity<T> authEntity(String token, T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        return new HttpEntity<>(body, headers);
    }

    public List<PatientEntity> listPatients(String token) {

        ResponseEntity<PatientEntity[]> response =
                restTemplate.exchange(
                        uriRestApiPatients, HttpMethod.GET, authEntity(token, null),
                        PatientEntity[].class);

        return Arrays.asList(response.getBody());
    }

    public void createPatient(PatientEntity patientData, String token) {
        try {
            restTemplate.exchange(uriRestApiPatients, HttpMethod.POST, authEntity(token, patientData), Void.class);
        } catch (Exception e) {
            log.severe("Error creating patient: " + e.getMessage());
        }
    }

    public PatientEntity updatePatient(PatientEntity patient, String token) {


        restTemplate.exchange(uriRestApiPatients + "/" + patient.getId(), HttpMethod.PUT, authEntity(token, patient), Void.class);

        return patient;
    }

    public void deletePatient(Integer id, String token) {

        //String url = "http://localhost:8000/patients/" + id;
        
        restTemplate.exchange(uriRestApiPatients + "/" + id, HttpMethod.DELETE, authEntity(token, null), Void.class);


    }

    public PatientEntity getPatientById(Integer id, String token) {
        ResponseEntity<PatientEntity> response = null;
        try {
            response = restTemplate.exchange(uriRestApiPatients + "/" + id, HttpMethod.GET, authEntity(token, null), PatientEntity.class);
        } catch (Exception e) {
            log.severe("Error creating patient: " + e.getMessage());
        }
        
        return response.getBody();

    }
}

/*public List<Map<String, Object>> listPatients(String jwt) {

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(jwt); // Agrega: Authorization: Bearer <token>

    HttpEntity<Void> entity = new HttpEntity<>(headers);

    ResponseEntity<List> response = restTemplate.exchange(
            "http://localhost:8000/patients",
            HttpMethod.GET,
            entity,
            List.class
    );

    return response.getBody();
} */