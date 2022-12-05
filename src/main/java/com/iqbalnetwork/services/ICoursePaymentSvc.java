package com.iqbalnetwork.services;

import com.iqbalnetwork.models.request.CoursePaymentRequest;
import com.iqbalnetwork.models.responses.CoursePaymentResponse;

public interface ICoursePaymentSvc {
    CoursePaymentResponse pay(CoursePaymentRequest request) throws Exception;
}
