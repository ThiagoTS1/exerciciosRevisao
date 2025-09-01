package com.academiadev.service;

import com.academiadev.exception.CourseException;
import com.academiadev.model.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseService {
    
    public void activateCourse(Course course) {
        course.setStatus(CourseStatus.ACTIVE);
    }
    
    public void deactivateCourse(Course course) {
        course.setStatus(CourseStatus.INACTIVE);
    }
    
    public List<Course> getCoursesByDifficultyLevel(List<Course> courses, DifficultyLevel level) {
        return courses.stream()
                .filter(course -> course.getDifficultyLevel() == level)
                .sorted((c1, c2) -> c1.getTitle().compareToIgnoreCase(c2.getTitle()))
                .collect(Collectors.toList());
    }
    
    public List<Course> getActiveCourses(List<Course> courses) {
        return courses.stream()
                .filter(course -> course.getStatus() == CourseStatus.ACTIVE)
                .collect(Collectors.toList());
    }
    
    public List<String> getUniqueInstructors(List<Course> courses) {
        return courses.stream()
                .filter(course -> course.getStatus() == CourseStatus.ACTIVE)
                .map(Course::getInstructorName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    public Course findCourseByTitle(List<Course> courses, String title) {
        return courses.stream()
                .filter(course -> course.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new CourseException("Curso não encontrado: " + title));
    }
}
