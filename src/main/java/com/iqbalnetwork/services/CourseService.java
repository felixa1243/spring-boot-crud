package com.iqbalnetwork.services;

import com.iqbalnetwork.controllers.exceptions.EntityExistException;
import com.iqbalnetwork.models.Course;
import com.iqbalnetwork.models.CourseType;
import com.iqbalnetwork.repository.CourseTypeRepo;
import com.iqbalnetwork.repository.ICourseRepos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("real_db")
@Transactional
public class CourseService implements ICourseServices<Course, String> {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICourseRepos repos;
    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private CourseTypeRepo courseTypeRepos;

    @Override
    public List<Course> getAll() {
        return repos.findAll();
    }

    @Override
    public Course create(Course course) throws Exception {
        try {
            Optional<CourseType> type = courseTypeRepos.findById(course.getCourseType().getCourseTypeId());
            System.out.println(type.get());
            course.setCourseType(type.get());
            return repos.save(course);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Optional<Course> get(String id) throws Exception {
        var result = repos.findById(id);
        if (result.isEmpty()) {
            throw new NoSuchElementException();
        }
        return result;
    }

    @Override
    public void update(Course course, String id) throws Exception {
        var result = get(id);
        result.get()
                .setId(id)
                .setDescription(course.getDescription())
                .setTitle(course.getTitle())
                .setLink(course.getLink())
        ;
        repos.save(result.get());
    }

    @Override
    public void delete(String id) throws Exception {
        var result = get(id);
        repos.delete(result.get());
    }

    @Override
    public Optional<List<Course>> getBy(String keyword, String value) {
        Optional<List<Course>> results = Optional.empty();
        List<Course> dump = new ArrayList<>();
        switch (keyword.toLowerCase()) {
            case "id":
                dump.add(repos
                        .findById(value).get());
                results = Optional.of(dump);
                break;
            case "title":
                var result = repos
                        .findAll().stream()
                        .filter(i -> i.getTitle()
                                .equalsIgnoreCase(value))
                        .collect(Collectors.toList());
                for (var i :
                        result) {
                    dump.add(i);
                }
                results = Optional.of(dump);
                break;
            case "description":
                var resultDesc = repos
                        .findAll().stream()
                        .filter(i -> i.getDescription()
                                .contains(value.toLowerCase()))
                        .collect(Collectors.toList());
                for (var i :
                        resultDesc) {
                    dump.add(i);
                }
                results = Optional.of(dump);
                break;
            default:
                throw new NoSuchElementException();
        }
        if (results.isEmpty()) {
            throw new NoSuchElementException();
        }
        return results;
    }
}
