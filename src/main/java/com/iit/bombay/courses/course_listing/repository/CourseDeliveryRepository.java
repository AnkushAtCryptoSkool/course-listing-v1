package com.iit.bombay.courses.course_listing.repository;

import com.iit.bombay.courses.course_listing.entity.Course;
import com.iit.bombay.courses.course_listing.entity.CourseDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseDeliveryRepository extends JpaRepository<CourseDelivery,Long> {
    @Query(value = "SELECT course_id FROM course_delivery WHERE year = :year AND semester = :semester", nativeQuery = true)
    List<String> getCourseIdsBasedOnYearAndSemester(@Param("year") String year, @Param("semester") String semester);

    @Query(value = "SELECT * FROM course_delivery WHERE year = :year AND semester = :semester AND course_id = :courseId", nativeQuery = true)
    Optional<CourseDelivery> getCourseIdsBasedOnYearAndSemesterAndCourseId(String year, String semester, String courseId);
}
