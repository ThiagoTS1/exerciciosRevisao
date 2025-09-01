package com.academiadev.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Enrollment {
    private Student student;
    private Course course;
    private int progress; // 0-100%
    private LocalDateTime enrollmentDate;
    
    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.progress = 0; // Progresso inicia em 0%
        this.enrollmentDate = LocalDateTime.now();
    }
    
    public Student getStudent() {
        return student;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public int getProgress() {
        return progress;
    }
    
    public void setProgress(int progress) {
        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException("Progresso deve estar entre 0 e 100%");
        }
        this.progress = progress;
    }
    
    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(student, that.student) && Objects.equals(course, that.course);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(student, course);
    }
    
    @Override
    public String toString() {
        return "Enrollment{" +
                "student=" + student.getEmail() +
                ", course=" + course.getTitle() +
                ", progress=" + progress + "%" +
                ", enrollmentDate=" + enrollmentDate +
                '}';
    }
}
