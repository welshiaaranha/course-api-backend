package com.iitb.courses.service;

import com.iitb.courses.model.CourseInstance;
import com.iitb.courses.model.Course;
import com.iitb.courses.repository.CourseInstanceRepository;
import com.iitb.courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class CourseInstanceService {

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    @Autowired
    private CourseRepository courseRepository;

    public CourseInstance createCourseInstance(CourseInstance instance) {
        // Validate course exists
        Course course = courseRepository.findByCourseId(instance.getCourse().getCourseId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found: " + instance.getCourse().getCourseId()));

        instance.setCourse(course);
        return courseInstanceRepository.save(instance);
    }

    public List<CourseInstance> getCourseInstancesByYearAndSemester(Integer year, Integer semester) {
        return courseInstanceRepository.findByYearAndSemester(year, semester);
    }

    public CourseInstance getCourseInstanceDetails(Integer year, Integer semester, String courseId) {
        return courseInstanceRepository.findByYearAndSemesterAndCourse_CourseId(year, semester, courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course instance not found"));
    }

    public void deleteCourseInstance(Integer year, Integer semester, String courseId) {
        CourseInstance instance = courseInstanceRepository.findByYearAndSemesterAndCourse_CourseId(year, semester, courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course instance not found"));

        courseInstanceRepository.delete(instance);
    }
}
