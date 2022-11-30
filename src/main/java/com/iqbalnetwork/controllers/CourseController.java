package com.iqbalnetwork.controllers;

import com.iqbalnetwork.models.Course;
import com.iqbalnetwork.models.requests.CourseRequest;
import com.iqbalnetwork.models.responses.ErrorResponse;
import com.iqbalnetwork.models.responses.SuccessResponse;
import com.iqbalnetwork.services.ICourseServices;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private ICourseServices courseServices;
    @Autowired
    private ModelMapper mapper;

    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam(defaultValue = "id") String key, @RequestParam String value) {
        try {
            return ResponseEntity.status(200)
                    .body(new SuccessResponse<>("Success", courseServices.getBy(key, value)));
        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .body(new ErrorResponse(404, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(200)
                .body(new SuccessResponse<List<Course>>("Success", courseServices.getAll()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") String id) throws Exception {
        return ResponseEntity
                .status(200)
                .body(new SuccessResponse<Optional<Course>>("success", courseServices.get(id)));
    }

    @PostMapping("/add")

    public ResponseEntity addCourse(@Valid @RequestBody CourseRequest course) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new SuccessResponse<Course>("Add data success", courseServices.create(mapper.map(course, Course.class)))
                );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {

        courseServices
                .delete(id);
        return ResponseEntity
                .status(204).body(new SuccessResponse<String>("delete data success", ""));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Course course) throws Exception {
        courseServices.update(course, id);
        return ResponseEntity
                .status(201)
                .body(new SuccessResponse<Course>("update success", course));
    }
}
