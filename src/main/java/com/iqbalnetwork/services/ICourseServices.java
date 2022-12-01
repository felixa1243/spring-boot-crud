package com.iqbalnetwork.services;

import com.iqbalnetwork.models.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseServices {
    List<Course> getAll();

    Course create(Course course) throws Exception;

    Optional<Course> get(String id) throws Exception;
    void update(Course course,String id) throws Exception;
    void delete(String id) throws Exception;
    List<Course> getBy(String keyword,String value) throws Exception;
}
