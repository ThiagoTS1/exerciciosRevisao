package com.academiadev.service;

import com.academiadev.exception.EnrollmentException;
import com.academiadev.model.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentService {
    
    public void enrollStudentInCourse(Student student, Course course) {
        // Verificar se o curso está ativo
        if (course.getStatus() != CourseStatus.ACTIVE) {
            throw new EnrollmentException("Não é possível se matricular em um curso inativo: " + course.getTitle());
        }
        
        // Verificar se o aluno já está matriculado no curso
        if (isStudentEnrolledInCourse(student, course)) {
            throw new EnrollmentException("Aluno já está matriculado no curso: " + course.getTitle());
        }
        
        // Verificar se o plano permite nova matrícula
        if (!student.canEnrollInNewCourse()) {
            throw new EnrollmentException("Plano " + student.getSubscriptionPlan().getPlanName() + 
                                        " não permite mais matrículas. Máximo: " + 
                                        student.getSubscriptionPlan().getMaxEnrollments());
        }
        
        // Criar matrícula
        Enrollment enrollment = new Enrollment(student, course);
        student.addEnrollment(enrollment);
    }
    
    public void cancelEnrollment(Student student, Course course) {
        Optional<Enrollment> enrollment = findEnrollment(student, course);
        
        if (enrollment.isPresent()) {
            student.removeEnrollment(enrollment.get());
        } else {
            throw new EnrollmentException("Aluno não está matriculado no curso: " + course.getTitle());
        }
    }
    
    public void updateProgress(Student student, Course course, int newProgress) {
        Optional<Enrollment> enrollment = findEnrollment(student, course);
        
        if (enrollment.isPresent()) {
            enrollment.get().setProgress(newProgress);
        } else {
            throw new EnrollmentException("Aluno não está matriculado no curso: " + course.getTitle());
        }
    }
    
    public List<Enrollment> getStudentEnrollments(Student student) {
        return student.getEnrollments();
    }
    
    public Optional<Enrollment> findEnrollment(Student student, Course course) {
        return student.getEnrollments().stream()
                .filter(e -> e.getCourse().equals(course))
                .findFirst();
    }
    
    public boolean isStudentEnrolledInCourse(Student student, Course course) {
        return findEnrollment(student, course).isPresent();
    }
    
    public double getAverageProgress() {
        // Implementação será feita no ReportService
        return 0.0;
    }
    
    public Optional<Student> getStudentWithMostEnrollments(List<Student> students) {
        return students.stream()
                .max((s1, s2) -> Integer.compare(s1.getActiveEnrollmentsCount(), s2.getActiveEnrollmentsCount()));
    }
}
