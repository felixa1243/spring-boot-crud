package com.iqbalnetwork.controllers;

import com.iqbalnetwork.models.CourseType;
import com.iqbalnetwork.models.request.CourseTypeDto;
import com.iqbalnetwork.models.responses.SuccessResponse;
import com.iqbalnetwork.services.ICourseServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("course-type")
public class CourseTypeController {
    @Autowired
    @Qualifier("course_type_svc")
    ICourseServices services;
    @Autowired
    ModelMapper mapper;

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody CourseTypeDto courseType) throws Exception {
        CourseType mapped = mapper.map (courseType, CourseType.class);
        return ResponseEntity
                .status(201)
                .body(
                        new SuccessResponse<>("success add data", services.create(mapped)));
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") String id) throws Exception {
        return ResponseEntity.status(200).body(
                new SuccessResponse<>("success get data", services.get(id)));
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(new SuccessResponse<>("success get data", services.getAll()));
    }
}
