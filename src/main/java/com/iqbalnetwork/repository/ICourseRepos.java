package com.iqbalnetwork.repository;

import com.iqbalnetwork.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepos extends JpaRepository<Course, String> {
//    @Modifying
//    Optional<List<Course>> getBy(String keyword, String value);
}
