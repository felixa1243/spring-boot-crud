package com.iqbalnetwork.controllers;

import com.iqbalnetwork.models.Course;
import com.iqbalnetwork.models.request.CourseDto;
import com.iqbalnetwork.models.responses.SuccessResponse;
import com.iqbalnetwork.services.ICourseServices;
import com.iqbalnetwork.utils.IRandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
    @Autowired
    IRandomString randomString;
    @Autowired
    @Qualifier("real_db")
    private ICourseServices courseServices;
    @Autowired
    private ModelMapper mapper;

    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam(defaultValue = "id") @NotBlank(message = "{invalid.title.required}") String key, @RequestParam String value) throws Exception {
        return ResponseEntity.status(200)
                .body(new SuccessResponse<>("Success", courseServices.getBy(key, value)));
    }

    @GetMapping
    public ResponseEntity getAll() {
        var results = courseServices.getAll();
        if (results.isEmpty()) {
            return ResponseEntity.status(204)
                    .body(new SuccessResponse<>("Current list is empty", results));
        }
        return ResponseEntity.status(200)
                .body(new SuccessResponse<List<Course>>("Success", results));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") String id) throws Exception {
        return ResponseEntity
                .status(200)
                .body(new SuccessResponse<Optional<Course>>("success", courseServices.get(id)));
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@Valid @RequestBody CourseDto course) throws Exception {
        Course mapped = mapper.map(course, Course.class);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new SuccessResponse<>("Add data success", courseServices.create(mapped))
                );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {

        try {
            courseServices
                    .delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity
                .status(204).body(new SuccessResponse<String>("delete data success", ""));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @Valid @RequestBody CourseDto course) throws Exception {
        var updated = mapper.map(course, Course.class);
        courseServices.update(updated, id);
        return ResponseEntity
                .status(201)
                .body(new SuccessResponse<Course>("update success", updated));
    }
}