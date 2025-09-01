package com.academiadev.service;

import com.academiadev.model.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReportService {
    
    public List<Course> getCoursesByDifficultyLevel(List<Course> courses, DifficultyLevel level) {
        return courses.stream()
                .filter(course -> course.getDifficultyLevel() == level)
                .sorted((c1, c2) -> c1.getTitle().compareToIgnoreCase(c2.getTitle()))
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
    
    public Map<String, List<Student>> groupStudentsBySubscriptionPlan(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(
                    student -> student.getSubscriptionPlan().getPlanName()
                ));
    }
    
    public double getAverageProgress(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getEnrollments().stream())
                .mapToInt(Enrollment::getProgress)
                .average()
                .orElse(0.0);
    }
    
    public Optional<Student> getStudentWithMostEnrollments(List<Student> students) {
        return students.stream()
                .max((s1, s2) -> Integer.compare(s1.getActiveEnrollmentsCount(), s2.getActiveEnrollmentsCount()));
    }
    
    public Map<DifficultyLevel, Long> getCourseCountByDifficultyLevel(List<Course> courses) {
        return courses.stream()
                .collect(Collectors.groupingBy(
                    Course::getDifficultyLevel,
                    Collectors.counting()
                ));
    }
    
    public Map<CourseStatus, Long> getCourseCountByStatus(List<Course> courses) {
        return courses.stream()
                .collect(Collectors.groupingBy(
                    Course::getStatus,
                    Collectors.counting()
                ));
    }
}
