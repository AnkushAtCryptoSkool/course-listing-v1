package com.iit.bombay.courses.course_listing.service.impl;

import com.iit.bombay.courses.course_listing.entity.Course;
import com.iit.bombay.courses.course_listing.entity.CourseDelivery;
import com.iit.bombay.courses.course_listing.repository.CourseDeliveryRepository;
import com.iit.bombay.courses.course_listing.repository.CourseRepository;
import com.iit.bombay.courses.course_listing.service.CourseDeliveryService;
import com.iit.bombay.courses.course_listing.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseDeliveryServiceImpl implements CourseDeliveryService {
    @Autowired
    CourseDeliveryRepository courseDeliveryRepository;

    @Autowired
    CourseRepository courseRepository;
    @Override
    public CourseDelivery save(CourseDelivery courseDelivery) {
        if(courseDelivery == null){
            throw new RuntimeException();
        }
        Optional<Course> courseById = courseRepository.findById(Long.valueOf(courseDelivery.getCourseId()));
        if(!courseById.isPresent())
             throw new RuntimeException("Course not exists provided id");

        return courseDeliveryRepository.save(courseDelivery);
    }

    @Override
    public List<Course> getCoursesByYearAndSemester(String year, String semester) {
        List<String> courseIds = courseDeliveryRepository.getCourseIdsBasedOnYearAndSemester(year,semester);
        if (courseIds.isEmpty())
            throw new RuntimeException("No Course Found For given query");

        List<Course> courseList = courseIds.stream().map((courseId) -> courseRepository.findById(Long.valueOf(courseId)).get()).collect(Collectors.toList());
        return courseList;
    }

    @Override
    public Course getCoursesByYearAndSemesterAndCourseId(String year, String semester, String courseId) {
        Optional<CourseDelivery> courseDelivery = courseDeliveryRepository.getCourseIdsBasedOnYearAndSemesterAndCourseId(year, semester, courseId);
        if (!courseId.equals(courseDelivery.get().getCourseId()))
            throw new RuntimeException("No Course Found For given query");
        Optional<Course> course = courseRepository.findById(Long.valueOf(courseDelivery.get().getCourseId()));
        if(course.isPresent())
            return course.get();
        else
            throw new RuntimeException("No Course Found For given id in course table, id : " + courseDelivery.get().getCourseId());
    }

    @Override
    public Boolean deleteCoursesByYearAndSemesterAndCourseId(String year, String semester, String courseId) {
        Optional<CourseDelivery> courseDelivery = courseDeliveryRepository.getCourseIdsBasedOnYearAndSemesterAndCourseId(year, semester, courseId);
        if(!courseDelivery.isPresent())
         return false;

        courseDeliveryRepository.deleteById(courseDelivery.get().getId());
        return true;
    }
}
