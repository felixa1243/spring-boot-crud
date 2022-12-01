package com.iqbalnetwork.services;

import com.iqbalnetwork.controllers.exceptions.EntityExistException;
import com.iqbalnetwork.models.Course;
import com.iqbalnetwork.repository.ICourseRepos;
import com.iqbalnetwork.utils.IRandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("real_db")
public class CourseServices implements ICourseServices {
    @Autowired
    ICourseRepos repos;
    @Autowired
    IRandomString randomString;

    @Override
    public List<Course> getAll() {
        return repos.findAll();
    }

    @Override
    public Course create(Course course) throws Exception {
        var result = repos.findAll()
                .stream()
                .filter(i -> i.getTitle().equalsIgnoreCase(course.getTitle()))
                .collect(Collectors.toList());
        if (!result.isEmpty()) {
            throw new EntityExistException();
        }
        course.setCourseId(randomString.random());
        return repos.save(course);
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
                .setCourseId(id)
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
    public List<Course> getBy(String keyword, String value) throws Exception {
        List<Course> results = new ArrayList<>();
        switch (keyword.toLowerCase()) {
            case "id":
                results.add(repos.findById(value).get());
                break;
            case "title":
                var result = repos
                        .findAll().stream()
                        .filter(i -> i.getTitle()
                                .equalsIgnoreCase(value))
                        .collect(Collectors.toList());
                for (var i :
                        result) {
                    results.add(i);
                }
                break;
            case "description":
                var resultDesc = repos
                        .findAll().stream()
                        .filter(i -> i.getDescription()
                                .contains(value.toLowerCase()))
                        .collect(Collectors.toList());
                for (var i :
                        resultDesc) {
                    results.add(i);
                }
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
