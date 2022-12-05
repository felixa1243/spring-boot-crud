package com.iqbalnetwork.services;

import com.iqbalnetwork.models.request.CoursePaymentRequest;
import com.iqbalnetwork.models.responses.CoursePaymentResponse;
import com.iqbalnetwork.repository.ICoursePaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursePayment implements ICoursePaymentSvc {
    @Autowired
    ICoursePaymentRepo repo;

    @Override
    public CoursePaymentResponse pay(CoursePaymentRequest request) throws Exception {
        return repo.callApi(request);
    }
}
