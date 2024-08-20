package com.iit.bombay.courses.course_listing.repository;

import com.iit.bombay.courses.course_listing.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> findByTitleContaining(String title);
}
