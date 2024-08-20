package com.iit.bombay.courses.course_listing.controller;


import com.iit.bombay.courses.course_listing.entity.Course;
import com.iit.bombay.courses.course_listing.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam(required = false) String title) {
        try {
            List<Course> courses = new ArrayList<>();

            if (title == null)
                courseService.findAll().forEach(courses::add);
            else
                courseService.findByTitleContaining(title).forEach(courses::add);

            if (courses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.info("all course list : {}",courses);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occured will getting course : {}",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable("id") long id) {
            try {
                Optional<Course> course = courseService.findById(id);
                return new ResponseEntity<>(course.get(), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
            }
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course courseSaved = courseService
                    .save(course);
            return new ResponseEntity<>(courseSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Exception occured while saving : {}",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") long id) {
        try {
            boolean isDeleted = courseService.deleteById(id);
            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
