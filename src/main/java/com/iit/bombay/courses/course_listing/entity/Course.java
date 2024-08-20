package com.iit.bombay.courses.course_listing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "code")
    @NotBlank
    private String code;

    @Column(name = "description")
    @NotBlank
    private String description;

}
