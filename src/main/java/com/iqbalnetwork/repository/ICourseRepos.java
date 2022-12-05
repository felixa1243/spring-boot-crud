package com.iqbalnetwork.repository;

import com.iqbalnetwork.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface ICourseRepos extends JpaRepository<Course, String> {
}
