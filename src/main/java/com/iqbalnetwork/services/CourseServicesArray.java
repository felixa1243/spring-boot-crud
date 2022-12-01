package com.iqbalnetwork.services;

public class CourseServicesArray {
//    @Autowired
//    private CourseArray courseRepository;
//
//    @Override
//    public List<Course> getAll() {
//        try {
//            return courseRepository.getAll();
//        } catch (Exception err) {
//            throw new RuntimeException(err);
//        }
//    }
//
//    @Override
//    public Course create(Course course) throws Exception {
//        courseRepository.create(course);
//        return course;
//    }
//
//    @Override
//    public Optional<Course> get(String id) throws Exception {
//        var result = courseRepository.findById(id);
//        if (result.isEmpty()) {
//            throw new NotFoundException();
//        }
//        return result;
//    }
//
//    @Override
//    public void update(Course course, String id) throws Exception {
//        var result = courseRepository.findById(id);
////        if (result.isEmpty()) {
////            throw new NotFoundException();
////        }
//        courseRepository.update(course, id);
//    }
//
//    @Override
//    public void delete(String id) throws Exception {
//        try {
//            courseRepository.delete(id);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public List<Course> getBy(String keyword, String value) throws Exception {
//        var result = courseRepository.getBy(keyword, value);
//        if (result.isEmpty() || result == null) {
//            throw new NotFoundException();
//        }
//        return result;
//    }
}