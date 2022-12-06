package com.iqbalnetwork.services;

import com.iqbalnetwork.models.CourseInfo;
import com.iqbalnetwork.repository.CourseInfoRepo;
import com.iqbalnetwork.utils.IRandomString;
import com.iqbalnetwork.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("course_info_svc")
public class CourseInfoService implements ICourseServices<CourseInfo, String> {
    @Autowired
    CourseInfoRepo repo;
    @Autowired
    IRandomString randomString;

    @Override
    public List<CourseInfo> getAll() {
        return repo.findAll();
    }

    @Override
    public CourseInfo create(CourseInfo course) throws Exception {
        return repo.save(course);
    }

    @Override
    public Optional<CourseInfo> get(String id) throws Exception {
        return Optional.empty();
    }

    @Override
    public void update(CourseInfo course, String id) throws Exception {

    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public Optional<List<CourseInfo>> getBy(String keyword, String value) throws Exception {
        return Optional.empty();
    }

    @Override
    public Page get(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Page<CourseInfo> getAll(List<SearchCriteria> searchCriteria, Pageable pageable) throws Exception {
        return null;
    }
}
