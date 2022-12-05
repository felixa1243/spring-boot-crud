package com.iqbalnetwork.repository;

import com.iqbalnetwork.controllers.exceptions.RestTemplateException;
import com.iqbalnetwork.models.request.CoursePaymentRequest;
import com.iqbalnetwork.models.responses.CoursePaymentResponse;
import com.iqbalnetwork.models.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;

@Repository
public class CoursePaymentRepo implements ICoursePaymentRepo {
    @Value("${service.payment.url}")
    private String paymentService;
    private RestTemplate restTemplate;

    public CoursePaymentRepo(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CoursePaymentResponse callApi(CoursePaymentRequest coursePaymentRequest) throws Exception {
        try {
            ResponseEntity<SuccessResponse> response = restTemplate.postForEntity(paymentService, coursePaymentRequest, SuccessResponse.class);

            SuccessResponse<String> paymentResponse = response.getBody();
            CoursePaymentResponse coursePaymentResponse = new CoursePaymentResponse();
            coursePaymentResponse.setStatus(true);
            coursePaymentResponse.setCoursePaymentId(paymentResponse.getData());
            return coursePaymentResponse;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new NoSuchElementException();
            }
            throw new RestTemplateException(paymentService, HttpStatus.SERVICE_UNAVAILABLE, "Service is down");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RestTemplateException(paymentService, HttpStatus.SERVICE_UNAVAILABLE, "Service time out");
        }
    }
}
