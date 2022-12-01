package com.iqbalnetwork.repository;

import com.iqbalnetwork.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseArray {
    List<Course> getAll() throws Exception;
    Course create(Course course) throws Exception;
    Optional<Course> findById(String id) throws Exception;
    void update(Course course, String id) throws Exception;
    void delete(String id) throws Exception;
    List<Course> getBy(String keyword,String value) throws Exception;
}
