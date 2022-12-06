package com.iqbalnetwork.repository.specifications;

import com.iqbalnetwork.models.Course;
import com.iqbalnetwork.models.CourseInfo;
import com.iqbalnetwork.models.CourseType;
import com.iqbalnetwork.utils.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CourseSpecification implements Specification<Course> {
    SearchCriteria searchCriteria;

    public CourseSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path<String> path;
        if (searchCriteria.getField().equals("typename")) {
            path = typeJoin(root).get(searchCriteria.getField());
        } else if (searchCriteria.getField().equals("duration") || searchCriteria.getField().equals("level")) {
            path = infoJoin(root).get(searchCriteria.getField());
        }
        path = root.get(searchCriteria.getField());
        switch (searchCriteria.getQueryOperator()) {
            default:
            case EQUALS:
                if (searchCriteria.getValue() instanceof String) {
                    return cb.equal(cb.lower(path), "%" + ((String) searchCriteria.getValue()).toLowerCase());
                }
                return cb.equal(path, "%");
            case DOESNT_EQUAL:
                if (searchCriteria.getValue() instanceof String) {
                    return cb.notEqual(cb.lower(path), "%" + ((String) searchCriteria.getValue()).toLowerCase() + "%");
                }
                return cb.notEqual(path, "%" + searchCriteria.getValue() + "%");
            case CONTAINS:
                if (searchCriteria.getValue() instanceof String) {
                    return cb.equal(cb.lower(path), "%" + ((String) searchCriteria.getValue()).toLowerCase() + "%");
                }
                return cb.like(path, "%" + searchCriteria.getValue() + "%");
            case DOESNT_CONTAIN:
                if (searchCriteria.getValue() instanceof String) {
                    return cb.equal(cb.lower(path), "%" + ((String) searchCriteria.getValue()).toLowerCase());
                }
                return cb.notLike(path, "%" + searchCriteria.getValue() + "%");
            case LESS_THAN:
                return cb.lessThan(path, searchCriteria.getValue().toString());
            case LESS_THAN_EQUAL:
                return cb.lessThanOrEqualTo(path, searchCriteria.getValue().toString());
            case GREATER_THAN:
                return cb.greaterThan(path, searchCriteria.getValue().toString());
            case GREATER_THAN_EQUAL:
                return cb.greaterThan(path, searchCriteria.getValue().toString());
        }
    }

    private Join<Course, CourseInfo> infoJoin(Root<Course> root) {
        return root.join("courseInfo");
    }

    private Join<Course, CourseType> typeJoin(Root<Course> root) {
        return root.join("courseType");
    }
}
