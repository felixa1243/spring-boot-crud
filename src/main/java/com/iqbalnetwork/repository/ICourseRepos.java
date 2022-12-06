package com.iqbalnetwork.repository;

import com.iqbalnetwork.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ICourseRepos extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {
    @Query(
            value = "SELECT * FROM m_course",
            countQuery = "SELECT count(*) FROM m_course",
            nativeQuery = true
    )
    Page<Course> findAllPaged(Pageable pageable);
}
