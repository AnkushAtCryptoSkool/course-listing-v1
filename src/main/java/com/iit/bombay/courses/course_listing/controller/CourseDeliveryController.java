package com.iit.bombay.courses.course_listing.controller;


import com.iit.bombay.courses.course_listing.entity.Course;
import com.iit.bombay.courses.course_listing.entity.CourseDelivery;
import com.iit.bombay.courses.course_listing.service.CourseDeliveryService;
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
public class CourseDeliveryController {

    @Autowired
    CourseDeliveryService courseDeliveryService;

    @GetMapping("/instances/{year}/{semester}")
    public ResponseEntity<?> getCoursesByYearAndSemester(@PathVariable String year,@PathVariable String semester) {
        try {
            return new ResponseEntity<>(courseDeliveryService.getCoursesByYearAndSemester(year,semester), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/instances/{year}/{semester}/{courseId}")
    public ResponseEntity<?> getCoursesByYearAndSemesterAndCourseId(@PathVariable String year,@PathVariable String semester,@PathVariable String courseId) {
        try {
            return new ResponseEntity<>(courseDeliveryService.getCoursesByYearAndSemesterAndCourseId(year,semester,courseId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/instances")
    public ResponseEntity<CourseDelivery> createCoursDelivery(@RequestBody CourseDelivery courseDelivery) {
        try {
            CourseDelivery courseDeliverySaved = courseDeliveryService
                    .save(courseDelivery);
            return new ResponseEntity<>(courseDeliverySaved, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Exception occured while saving : {}",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/instances/{year}/{semester}/{courseId}")
    public ResponseEntity<?> deleteCoursesByYearAndSemesterAndCourseId(@PathVariable String year,@PathVariable String semester,@PathVariable("courseId") String id) {
        try {
            Boolean isDeleted = courseDeliveryService.deleteCoursesByYearAndSemesterAndCourseId(year,semester,id);
            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Course Delivery entry not found for the query ", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
