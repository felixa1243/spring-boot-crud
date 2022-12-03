package com.iqbalnetwork.services;

import com.iqbalnetwork.models.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseServices<T,R> {
    List<T> getAll();

    T create(T course) throws Exception;

    Optional<T> get(String id) throws Exception;
    void update(T course,String id) throws Exception;
    void delete(String id) throws Exception;
    Optional<List<T>> getBy(String keyword, String value) throws Exception;
}
