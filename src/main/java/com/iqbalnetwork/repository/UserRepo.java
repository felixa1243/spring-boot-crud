package com.iqbalnetwork.repository;

import com.iqbalnetwork.models.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserRepo implements IUserRepo {
    @Value("${service.user.uri}")
    private String url;
    private RestTemplate restTemplate;

    public UserRepo(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserResponse[] getAll() {
        try {
            ResponseEntity<UserResponse[]> response = restTemplate.getForEntity(url, UserResponse[].class);
            //restTemplate. get -> dari url -> ubah ke array of UserResponse
            return response.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public UserResponse getById(String id) {
        try {
            ResponseEntity<UserResponse> response = restTemplate.getForEntity(url + "/" + id, UserResponse.class);
            return response.getBody();
        } catch (Exception err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
}
