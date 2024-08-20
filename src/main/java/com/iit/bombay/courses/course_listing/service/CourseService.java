package com.iit.bombay.courses.course_listing.service;

import com.iit.bombay.courses.course_listing.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();

    List<Course> findByTitleContaining(String title);

    Optional<Course> findById(long id);

    Course save(Course course);

    Boolean deleteById(long id);
}
