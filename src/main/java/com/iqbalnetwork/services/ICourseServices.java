package com.iqbalnetwork.services;

import com.iqbalnetwork.models.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseServices {
    List<Course> getAll();

    Course create(Course course);

    Optional<Course> get(String id);
    void update(Course course,String id);
    void delete(String id);
}
