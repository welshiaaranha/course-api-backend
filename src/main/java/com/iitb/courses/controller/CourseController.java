package com.iitb.courses.controller;

import com.iitb.courses.model.Course;
import com.iitb.courses.service.CourseService;
import com.iitb.courses.service.CourseInstanceService;
import com.iitb.courses.model.CourseInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iitb.courses.model.CourseRequest;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*") // Allow frontend to access it
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseInstanceService courseInstanceService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable String courseId) {
        return courseService.getCourseByCourseId(courseId);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseRequest request) {
        Course created = courseService.createCourse(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok("Course deleted successfully");
    }

    // Course Instance Endpoints

    @PostMapping("/instances")
    public ResponseEntity<?> createCourseInstance(@RequestBody CourseInstance instance) {
        CourseInstance created = courseInstanceService.createCourseInstance(instance);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/instances/{year}/{semester}")
    public List<CourseInstance> getCourseInstances(@PathVariable Integer year, @PathVariable Integer semester) {
        return courseInstanceService.getCourseInstancesByYearAndSemester(year, semester);
    }

    @GetMapping("/instances/{year}/{semester}/{courseId}")
    public CourseInstance getCourseInstance(@PathVariable Integer year, @PathVariable Integer semester, @PathVariable String courseId) {
        return courseInstanceService.getCourseInstanceDetails(year, semester, courseId);
    }

    @DeleteMapping("/instances/{year}/{semester}/{courseId}")
    public ResponseEntity<?> deleteCourseInstance(@PathVariable Integer year, @PathVariable Integer semester, @PathVariable String courseId) {
        courseInstanceService.deleteCourseInstance(year, semester, courseId);
        return ResponseEntity.ok("Course instance deleted successfully");
    }
}
