package com.iqbalnetwork.services;

import com.iqbalnetwork.models.Course;
import com.iqbalnetwork.repository.ICourseRepos;
import com.iqbalnetwork.repository.specifications.CourseSpecification;
import com.iqbalnetwork.utils.SearchCriteria;
import com.iqbalnetwork.utils.constant.SearchOperation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Qualifier("real_db")
@Transactional
public class CourseService implements ICourseServices<Course, String> {
    @Getter
    private final Path root = Paths.get("/home/user/latihan/assets");
    @Autowired
    private ICourseRepos repos;

    @Override
    public List<Course> getAll() {
        return repos.findAll();
    }

    @Override
    public Course create(Course course) throws Exception {
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
                .setId(id)
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

    @Override
    public Page get(Pageable pageable) {
        return repos.findAll(pageable);
    }

    @Override
    public Page<Course> getAll(List<SearchCriteria> searchCriteria, Pageable pageable) throws Exception {
        Specification<Course> courseSpecification = Specification.where(new CourseSpecification(searchCriteria.get(0)));

        for (int i = 1; i < searchCriteria.size(); i++) {
            SearchCriteria searchCriterion = searchCriteria.get(i);
            CourseSpecification newSpecs = new CourseSpecification(
                    searchCriterion
            );
            if (searchCriterion.getSearchOperation() == SearchOperation.AND) {
                courseSpecification = Specification.where(courseSpecification).and(newSpecs);
            } else {
                courseSpecification = Specification.where(courseSpecification).or(newSpecs);
            }
        }
        return repos.findAll(courseSpecification, pageable);
    }

    //    @Override
//    public void createFolder(String foldername) {
//        File dir = new File(root.toString() + "/" + foldername);
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
//    }
//
//    @Override
//    public void createFile(MultipartFile file) throws Exception {
//        Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
//    }
}
