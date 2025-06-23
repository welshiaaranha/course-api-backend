package com.iitb.courses.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "\"year\"")
    private Integer year;

    @NotNull
    private Integer semester;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
