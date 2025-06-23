package com.iitb.courses.service;

import com.iitb.courses.model.Course;
import com.iitb.courses.model.CourseRequest;
import com.iitb.courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // ✅ This is your newly added method
    public Course getCourseByCourseId(String courseId) {
        return courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found: " + courseId));
    }

    public Course createCourse(CourseRequest request) {
        List<String> prerequisites = request.getPrerequisites() != null ? request.getPrerequisites() : new ArrayList<>();

        List<String> missingPrereqs = prerequisites.stream()
                .filter(id -> !courseRepository.findByCourseId(id).isPresent())
                .collect(Collectors.toList());

        if (!missingPrereqs.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid prerequisites: " + String.join(", ", missingPrereqs)
            );
        }

        List<Course> prerequisiteCourses = prerequisites.stream()
                .map(id -> courseRepository.findByCourseId(id).get())
                .collect(Collectors.toList());

        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setCourseId(request.getCourseId());
        course.setDescription(request.getDescription());
        course.setPrerequisites(prerequisiteCourses);

        return courseRepository.save(course);
    }

    public void deleteCourse(String courseId) {
        Course course = courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found: " + courseId));

        // Check if this course is a prerequisite for any other course
        boolean isPrerequisite = courseRepository.findAll().stream()
                .anyMatch(c -> c.getPrerequisites().contains(course));

        if (isPrerequisite) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cannot delete course " + courseId + " as it is a prerequisite for other courses."
            );
        }

        courseRepository.delete(course);
    }
}
