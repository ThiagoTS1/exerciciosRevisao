package com.academiadev.util;

import com.academiadev.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class InitialData {
    
    public static List<Course> createInitialCourses() {
        List<Course> courses = new ArrayList<>();
        
        courses.add(new Course("Java Básico", "Fundamentos da linguagem Java", "João Silva", 40, DifficultyLevel.BEGINNER));
        courses.add(new Course("Java Avançado", "Recursos avançados do Java", "Maria Santos", 60, DifficultyLevel.ADVANCED));
        courses.add(new Course("Spring Framework", "Desenvolvimento com Spring", "Carlos Oliveira", 50, DifficultyLevel.INTERMEDIATE));
        courses.add(new Course("Banco de Dados", "SQL e modelagem de dados", "Ana Costa", 35, DifficultyLevel.BEGINNER));
        courses.add(new Course("Microserviços", "Arquitetura de microserviços", "Pedro Lima", 45, DifficultyLevel.ADVANCED));
        courses.add(new Course("React Básico", "Introdução ao React", "Lucia Ferreira", 30, DifficultyLevel.BEGINNER));
        courses.add(new Course("DevOps", "Práticas de DevOps", "Roberto Alves", 55, DifficultyLevel.INTERMEDIATE));
        
        return courses;
    }
    
    public static List<User> createInitialUsers() {
        List<User> users = new ArrayList<>();
        
        // Admins
        users.add(new Admin("Admin Principal", "admin@academiadev.com"));
        users.add(new Admin("Gerente de Cursos", "gerente@academiadev.com"));
        
        // Students com Basic Plan
        users.add(new Student("João Aluno", "joao@email.com", new BasicPlan()));
        users.add(new Student("Maria Estudante", "maria@email.com", new BasicPlan()));
        users.add(new Student("Pedro Aprendiz", "pedro@email.com", new BasicPlan()));
        
        // Students com Premium Plan
        users.add(new Student("Ana Premium", "ana@email.com", new PremiumPlan()));
        users.add(new Student("Carlos VIP", "carlos@email.com", new PremiumPlan()));
        users.add(new Student("Lucia Pro", "lucia@email.com", new PremiumPlan()));
        
        return users;
    }
    
    public static List<Student> getStudentsFromUsers(List<User> users) {
        return users.stream()
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .collect(Collectors.toList());
    }
    
    public static List<Admin> getAdminsFromUsers(List<User> users) {
        return users.stream()
                .filter(user -> user instanceof Admin)
                .map(user -> (Admin) user)
                .collect(Collectors.toList());
    }
    
    public static void createInitialEnrollments(List<Student> students, List<Course> courses) {
        // João se matricula em 2 cursos (Basic Plan permite até 3)
        if (students.size() > 0 && courses.size() > 0) {
            Student joao = students.get(0);
            joao.addEnrollment(new Enrollment(joao, courses.get(0))); // Java Básico
            joao.addEnrollment(new Enrollment(joao, courses.get(3))); // Banco de Dados
        }
        
        // Maria se matricula em 1 curso (Basic Plan permite até 3)
        if (students.size() > 1 && courses.size() > 2) {
            Student maria = students.get(1);
            maria.addEnrollment(new Enrollment(maria, courses.get(2))); // Spring Framework
        }
        
        // Ana Premium se matricula em vários cursos (Premium Plan permite ilimitado)
        if (students.size() > 3 && courses.size() > 4) {
            Student ana = students.get(3);
            ana.addEnrollment(new Enrollment(ana, courses.get(1))); // Java Avançado
            ana.addEnrollment(new Enrollment(ana, courses.get(4))); // Microserviços
            ana.addEnrollment(new Enrollment(ana, courses.get(6))); // DevOps
        }
        
        // Definir alguns progressos
        if (students.size() > 0 && students.get(0).getEnrollments().size() > 0) {
            students.get(0).getEnrollments().get(0).setProgress(75); // João 75% em Java Básico
        }
        
        if (students.size() > 1 && students.get(1).getEnrollments().size() > 0) {
            students.get(1).getEnrollments().get(0).setProgress(30); // Maria 30% em Spring
        }
        
        if (students.size() > 3 && students.get(3).getEnrollments().size() > 0) {
            students.get(3).getEnrollments().get(0).setProgress(90); // Ana 90% em Java Avançado
        }
    }
}
