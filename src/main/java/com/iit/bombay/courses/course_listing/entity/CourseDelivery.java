package com.iit.bombay.courses.course_listing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "course_delivery")
public class CourseDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String year;
    @NotBlank
    private String semester;

    @NotBlank
    @Column(name = "course_id")
    private String courseId;
}
