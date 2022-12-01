package com.iqbalnetwork.repository;

import com.iqbalnetwork.models.Course;
import com.iqbalnetwork.utils.IRandomString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseArrayRepos implements CourseArray {
    private List<Course> courses = new ArrayList<>();
    @Autowired
    private IRandomString randomString;

    @Override
    public List<Course> getAll() throws Exception {
        return courses;
    }

    @Override
    public Course create(Course course) throws Exception {
        course.setCourseId(randomString.random());
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        for (Course course :
                courses) {
            if (course.getCourseId().equals(id)) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) throws Exception {
        for (Course existingCourse : courses) {
            if (existingCourse.getCourseId().equals(id)) {
                existingCourse.setTitle(course.getTitle());
                existingCourse.setDescription(course.getDescription());
                existingCourse.setLink(course.getLink());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
//        for (Course course : courses) {
//            if (course.getCourseId() == id) {
//                courses.remove(Integer.parseInt(id));
//                break;
//            }
//        }
        courses.remove(Optional.of(findById(id)));
    }

    @Override
    public List<Course> getBy(String keyword, String value) throws Exception {
        List<Course> result;
        switch (keyword.toLowerCase()) {
            case "title":
                result = courses.stream().filter(i -> i.getTitle().toLowerCase().equalsIgnoreCase(value)).collect(Collectors.toList());
                break;
            case "id":
                result = courses.stream()
                        .filter(i -> i.getCourseId()
                                .toLowerCase()
                                .equalsIgnoreCase(value))
                        .collect(Collectors.toList());
                break;
            case "description":
                result = courses.stream()
                        .filter(i -> i.getDescription().toLowerCase().contains(value.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case "link":
                result = courses.stream()
                        .filter(i -> i.getLink().equalsIgnoreCase(value))
                        .collect(Collectors.toList());
            default:
                return null;
        }
        return result;
    }
}
