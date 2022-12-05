package com.iqbalnetwork.controllers;

import com.iqbalnetwork.models.request.CoursePaymentRequest;
import com.iqbalnetwork.services.ICoursePaymentSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("course-payment")
public class CoursePaymentController {
    @Autowired
    ICoursePaymentSvc coursePaymentSvc;

    @PostMapping
    public ResponseEntity pay(@RequestBody CoursePaymentRequest req) throws Exception {
        return ResponseEntity.status(201).body(coursePaymentSvc.pay(req));
    }
}
