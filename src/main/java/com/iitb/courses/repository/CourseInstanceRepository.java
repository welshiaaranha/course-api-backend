package com.iitb.courses.repository;

import com.iitb.courses.model.CourseInstance;
import com.iitb.courses.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
    List<CourseInstance> findByYearAndSemester(Integer year, Integer semester);
    Optional<CourseInstance> findByYearAndSemesterAndCourse_CourseId(Integer year, Integer semester, String courseId);
    void deleteByYearAndSemesterAndCourse_CourseId(Integer year, Integer semester, String courseId);
}
