package com.iqbalnetwork.services;

import com.iqbalnetwork.models.CourseType;
import com.iqbalnetwork.repository.CourseTypeRepo;
import com.iqbalnetwork.utils.IRandomString;
import com.iqbalnetwork.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Qualifier("course_type_svc")
public class CourseTypeService implements ICourseServices<CourseType, String> {
    @Autowired
    CourseTypeRepo repo;
    @Autowired
    IRandomString randomString;

    @Override
    public List<CourseType> getAll() {
        return repo.findAll();
    }

    @Override
    public CourseType create(CourseType courseType) throws Exception {
        return repo.save(courseType);
    }

    @Override
    public Optional<CourseType> get(String id) throws Exception {
        Optional<CourseType> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new NoSuchElementException();
        }
        return result;
    }

    @Override
    public void update(CourseType course, String id) throws Exception {

    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public Optional<List<CourseType>> getBy(String keyword, String value) throws Exception {
        return Optional.empty();
    }

    @Override
    public Page get(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Page<CourseType> getAll(List<SearchCriteria> searchCriteria, Pageable pageable) throws Exception {
        return null;
    }
}
