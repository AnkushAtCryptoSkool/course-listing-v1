package com.iit.bombay.courses.course_listing.service.impl;

import com.iit.bombay.courses.course_listing.entity.Course;
import com.iit.bombay.courses.course_listing.repository.CourseDeliveryRepository;
import com.iit.bombay.courses.course_listing.repository.CourseRepository;
import com.iit.bombay.courses.course_listing.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseDeliveryRepository courseDeliveryRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findByTitleContaining(String title) {
        return courseRepository.findByTitleContaining(title);
    }

    @Override
    public Optional<Course> findById(long id) {
        return Optional.ofNullable(courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course Not Found with id : "+id)));
    }

    @Override
    public Course save(Course course) {
        if(course == null){
            throw new RuntimeException();
        }
        return courseRepository.save(course);
    }

    @Override
    public Boolean deleteById(long id) {
        if(!findById(id).isPresent())
            return false;

         courseRepository.deleteById(id);
        return true;
    }
}
