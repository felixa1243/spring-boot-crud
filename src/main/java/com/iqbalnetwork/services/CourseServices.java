package com.iqbalnetwork.services;

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
    public Course create(Course course) {
        try {
            courseRepository.create(course);
            return course;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Course> get(String id) {
        try {
            return courseRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Course course, String id) {
        try {
            courseRepository.update(course, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            courseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
