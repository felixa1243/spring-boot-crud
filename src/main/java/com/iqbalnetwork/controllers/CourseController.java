package com.iqbalnetwork.controllers;

import com.iqbalnetwork.models.Course;
import com.iqbalnetwork.models.requests.CourseRequest;
import com.iqbalnetwork.services.ICourseServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private ICourseServices courseServices;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(courseServices.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") String id) {
        return ResponseEntity.status(200).body(courseServices.get(id));
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody CourseRequest course) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        courseServices.create(mapper.map(course, Course.class))
                );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        courseServices.delete(id);
        return ResponseEntity.status(204).build();
    }
}
