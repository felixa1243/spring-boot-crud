package com.iqbalnetwork.repository;

import com.iqbalnetwork.models.request.CoursePaymentRequest;
import com.iqbalnetwork.models.responses.CoursePaymentResponse;
import org.springframework.stereotype.Repository;

public interface ICoursePaymentRepo {
    CoursePaymentResponse callApi(CoursePaymentRequest coursePaymentRequest) throws Exception;
}
