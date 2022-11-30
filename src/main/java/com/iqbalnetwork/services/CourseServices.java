package com.iqbalnetwork.services;

import com.iqbalnetwork.controllers.exceptions.EmptyFieldException;
import com.iqbalnetwork.controllers.exceptions.NotFoundException;
import com.iqbalnetwork.models.Course;
import com.iqbalnetwork.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServices implements ICourseServices {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAll() {
        try {
            return courseRepository.getAll();
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    public Course create(Course course) throws Exception {
        var isNotValid = (course.getTitle().isBlank());
        if (isNotValid) {
            throw new EmptyFieldException();
        }
        courseRepository.create(course);
        return course;
    }

    @Override
    public Optional<Course> get(String id) throws Exception {
        var result = courseRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException();
        }
        return result;
    }

    @Override
    public void update(Course course, String id) throws Exception {
        var result = courseRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException();
        }
        courseRepository.update(course, id);
    }

    @Override
    public void delete(String id) {
        try {
            courseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> getBy(String keyword, String value) throws Exception {
        var result = courseRepository.getBy(keyword, value);
        if (result.isEmpty() || result == null) {
            throw new NotFoundException();
        }
        return result;
    }
}