package com.iqbalnetwork.controllers;

import com.iqbalnetwork.models.Course;
import com.iqbalnetwork.models.CourseInfo;
import com.iqbalnetwork.models.CourseType;
import com.iqbalnetwork.models.request.CourseDto;
import com.iqbalnetwork.models.request.CourseReqWithFile;
import com.iqbalnetwork.models.responses.CommonResponse;
import com.iqbalnetwork.models.responses.PagedResponse;
import com.iqbalnetwork.models.responses.SuccessResponse;
import com.iqbalnetwork.services.ICourseServices;
import com.iqbalnetwork.utils.SearchCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
    @Autowired
    @Qualifier("real_db")
    private ICourseServices courseServices;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    @Qualifier("course_type_svc")
    private ICourseServices courseTypeSvc;

    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam(defaultValue = "id") @NotBlank(message = "{invalid.title.required}") String key, @RequestParam String value) throws Exception {
        return ResponseEntity.status(200)
                .body(new SuccessResponse<>("Success", courseServices.getBy(key, value)));
    }

//    @GetMapping
//    public ResponseEntity getAll() {
//        var results = courseServices.getAll();
//        if (results.isEmpty()) {
//            return ResponseEntity.status(204)
//                    .body(new SuccessResponse<>("Current list is empty", results));
//        }
//        return ResponseEntity.status(200)
//                .body(new SuccessResponse<List<Course>>("Success", results));
//    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") String id) throws Exception {
        return ResponseEntity
                .status(200)
                .body(new SuccessResponse<Optional<Course>>("success", courseServices.get(id)));
    }

    @PostMapping
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {
            "multipart/form-data"
    })
    public ResponseEntity addCourse(CourseReqWithFile course) throws Exception {

        CourseDto courseDto = mapper.map(course, CourseDto.class);
        Optional<CourseType> courseType = courseTypeSvc.get(course.getCourseTypeId());
        if (courseType.isPresent()) {
            courseDto.setCourseType(courseType.get());
        }

        CourseInfo courseInfo = new CourseInfo()
                .setDuration(course.getCourseDuration())
                .setLevel(course.getCourseLevel());

        courseDto.setCourseInfo(courseInfo);
        Course mapped = mapper.map(courseDto, Course.class);

        String filename = course.getFile().getOriginalFilename();
        MultipartFile file = course.getFile();
        System.out.println(file.getOriginalFilename());

        Path root = Paths.get("/home/user/latihan/assets/course/");
        try {
            Files.copy(course.getFile().getInputStream(), root.resolve(filename));
        } catch (Exception err) {
            throw new RuntimeException("Couldn't store the file Error: " + err.getMessage());
        }

        mapped.setFileUrl(root.toString());
        mapped.setFileName(course.getFile().getOriginalFilename());
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

    @GetMapping
    public ResponseEntity<CommonResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "useNative", required = false) boolean useNative,
            @RequestBody(required = false) List<SearchCriteria> criteria
    ) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        if (useNative) {
            PagedResponse<Course> pagedResponse = new PagedResponse<>(courseServices.get(pageable));
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                    "Success get data",
                    pagedResponse
            ));
        }
        Page<Course> courses;
        if (criteria != null) {
            courses = courseServices.getAll(criteria, pageable);
        } else {
            courses = courseServices.get(pageable);
        }
        CommonResponse response = new SuccessResponse<>(
                "Success get all data", new PagedResponse<>(courses));
        return ResponseEntity.status(200).body(response);
    }
}