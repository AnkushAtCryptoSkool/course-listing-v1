package com.iit.bombay.courses.course_listing.service;

import com.iit.bombay.courses.course_listing.entity.Course;
import com.iit.bombay.courses.course_listing.entity.CourseDelivery;

import java.util.List;
import java.util.Optional;

public interface CourseDeliveryService {

    CourseDelivery save(CourseDelivery courseDelivery);

    List<Course> getCoursesByYearAndSemester(String year, String semester);

    Course getCoursesByYearAndSemesterAndCourseId(String year, String semester, String courseId);
    Boolean deleteCoursesByYearAndSemesterAndCourseId(String year, String semester, String courseId);
}
