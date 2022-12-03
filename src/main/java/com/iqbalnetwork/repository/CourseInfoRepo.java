package com.iqbalnetwork.repository;

import com.iqbalnetwork.models.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseInfoRepo extends JpaRepository<CourseInfo,String> {
}
