package com.iqbalnetwork.services;

import com.iqbalnetwork.utils.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICourseServices<T, R> {
    List<T> getAll();

    T create(T course) throws Exception;

    Optional<T> get(String id) throws Exception;

    void update(T course, String id) throws Exception;

    void delete(String id) throws Exception;

    Optional<List<T>> getBy(String keyword, String value) throws Exception;
    Page get(Pageable pageable);
    Page<T> getAll(List<SearchCriteria> searchCriteria, Pageable pageable) throws Exception;
}
