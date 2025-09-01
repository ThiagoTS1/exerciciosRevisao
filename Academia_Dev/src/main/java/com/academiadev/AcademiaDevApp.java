package com.academiadev;

import com.academiadev.model.*;
import com.academiadev.service.*;
import com.academiadev.util.*;
import java.util.*;
import java.util.stream.Collectors;

public class AcademiaDevApp {
    private static List<Course> courses;
    private static List<User> users;
    private static List<Student> students;
    private static List<Admin> admins;
    private static User currentUser;
    private static Scanner scanner;
    
    private static CourseService courseService;
    private static EnrollmentService enrollmentService;
    private static SupportService supportService;
    private static ReportService reportService;
    
    public static void main(String[] args) {
        initializeApp();
        showMainMenu();
    }
    
    private static void initializeApp() {
        // Inicializar dados
        courses = InitialData.createInitialCourses();
        users = InitialData.createInitialUsers();
        students = InitialData.getStudentsFromUsers(users);
        admins = InitialData.getAdminsFromUsers(users);
        InitialData.createInitialEnrollments(students, courses);
        
        // Inicializar serviços
        courseService = new CourseService();
        enrollmentService = new EnrollmentService();
        supportService = new SupportService();
        reportService = new ReportService();
        
        scanner = new Scanner(System.in);
        
        System.out.println("=== ACADEMIA DEV - Plataforma de Cursos Online ===");
        System.out.println("Bem-vindo ao protótipo da AcademiaDev!");
        System.out.println();
    }
    
    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Fazer Login");
            System.out.println("2. Consultar Catálogo de Cursos");
            System.out.println("3. Abrir Ticket de Suporte");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    showCourseCatalog();
                    break;
                case 3:
                    createSupportTicket();
                    break;
                case 4:
                    System.out.println("Obrigado por usar a AcademiaDev!");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void login() {
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine().trim();
        
        Optional<User> userOpt = users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
        
        if (userOpt.isPresent()) {
            currentUser = userOpt.get();
            System.out.println("Login realizado com sucesso! Bem-vindo, " + currentUser.getName());
            
            if (currentUser instanceof Admin) {
                showAdminMenu();
            } else if (currentUser instanceof Student) {
                showStudentMenu();
            }
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }
    
    private static void showAdminMenu() {
        while (currentUser instanceof Admin) {
            System.out.println("\n=== MENU ADMINISTRADOR ===");
            System.out.println("1. Gerenciar Status de Cursos");
            System.out.println("2. Gerenciar Planos de Alunos");
            System.out.println("3. Atender Tickets de Suporte");
            System.out.println("4. Gerar Relatórios e Análises");
            System.out.println("5. Exportar Dados");
            System.out.println("6. Fazer Logout");
            System.out.print("Escolha uma opção: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    manageCourseStatus();
                    break;
                case 2:
                    manageStudentPlans();
                    break;
                case 3:
                    processSupportTickets();
                    break;
                case 4:
                    showReports();
                    break;
                case 5:
                    exportData();
                    break;
                case 6:
                    logout();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void showStudentMenu() {
        while (currentUser instanceof Student) {
            System.out.println("\n=== MENU ALUNO ===");
            System.out.println("1. Matricular-se em Curso");
            System.out.println("2. Consultar Matrículas");
            System.out.println("3. Atualizar Progresso");
            System.out.println("4. Cancelar Matrícula");
            System.out.println("5. Fazer Logout");
            System.out.print("Escolha uma opção: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    enrollInCourse();
                    break;
                case 2:
                    showEnrollments();
                    break;
                case 3:
                    updateProgress();
                    break;
                case 4:
                    cancelEnrollment();
                    break;
                case 5:
                    logout();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void manageCourseStatus() {
        System.out.println("\n=== GERENCIAR STATUS DE CURSOS ===");
        showCourseCatalog();
        
        System.out.print("Digite o título do curso: ");
        String title = scanner.nextLine().trim();
        
        try {
            Course course = courseService.findCourseByTitle(courses, title);
            System.out.println("Curso encontrado: " + course);
            System.out.println("Status atual: " + course.getStatus());
            
            System.out.println("1. Ativar curso");
            System.out.println("2. Inativar curso");
            System.out.print("Escolha uma opção: ");
            
            int choice = getIntInput();
            
            if (choice == 1) {
                courseService.activateCourse(course);
                System.out.println("Curso ativado com sucesso!");
            } else if (choice == 2) {
                courseService.deactivateCourse(course);
                System.out.println("Curso inativado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static void manageStudentPlans() {
        System.out.println("\n=== GERENCIAR PLANOS DE ALUNOS ===");
        
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println((i + 1) + ". " + student.getName() + " - " + 
                              student.getSubscriptionPlan().getPlanName());
        }
        
        System.out.print("Escolha o aluno (número): ");
        int studentIndex = getIntInput() - 1;
        
        if (studentIndex >= 0 && studentIndex < students.size()) {
            Student student = students.get(studentIndex);
            System.out.println("Aluno selecionado: " + student.getName());
            System.out.println("Plano atual: " + student.getSubscriptionPlan().getPlanName());
            
            System.out.println("1. Basic Plan (máx. 3 cursos)");
            System.out.println("2. Premium Plan (ilimitado)");
            System.out.print("Escolha o novo plano: ");
            
            int planChoice = getIntInput();
            
            if (planChoice == 1) {
                student.setSubscriptionPlan(new BasicPlan());
                System.out.println("Plano alterado para Basic!");
            } else if (planChoice == 2) {
                student.setSubscriptionPlan(new PremiumPlan());
                System.out.println("Plano alterado para Premium!");
            }
        }
    }
    
    private static void processSupportTickets() {
        System.out.println("\n=== ATENDER TICKETS DE SUPORTE ===");
        
        if (!supportService.hasPendingTickets()) {
            System.out.println("Não há tickets pendentes!");
            return;
        }
        
        System.out.println("Tickets pendentes: " + supportService.getPendingTicketsCount());
        
        Optional<SupportTicket> ticket = supportService.getNextTicket();
        if (ticket.isPresent()) {
            SupportTicket t = ticket.get();
            System.out.println("Ticket processado:");
            System.out.println("Título: " + t.getTitle());
            System.out.println("Mensagem: " + t.getMessage());
            System.out.println("Usuário: " + t.getUser().getName());
            System.out.println("Data: " + t.getCreationDate());
        }
    }
    
    private static void showReports() {
        System.out.println("\n=== RELATÓRIOS E ANÁLISES ===");
        System.out.println("1. Cursos por nível de dificuldade");
        System.out.println("2. Instrutores únicos");
        System.out.println("3. Alunos por plano");
        System.out.println("4. Média geral de progresso");
        System.out.println("5. Aluno com mais matrículas");
        System.out.print("Escolha um relatório: ");
        
        int choice = getIntInput();
        
        switch (choice) {
            case 1:
                showCoursesByDifficulty();
                break;
            case 2:
                showUniqueInstructors();
                break;
            case 3:
                showStudentsByPlan();
                break;
            case 4:
                showAverageProgress();
                break;
            case 5:
                showStudentWithMostEnrollments();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private static void showCoursesByDifficulty() {
        System.out.println("\n=== CURSOS POR NÍVEL DE DIFICULDADE ===");
        System.out.println("1. BEGINNER");
        System.out.println("2. INTERMEDIATE");
        System.out.println("3. ADVANCED");
        System.out.print("Escolha o nível: ");
        
        int choice = getIntInput();
        DifficultyLevel level = null;
        
        switch (choice) {
            case 1: level = DifficultyLevel.BEGINNER; break;
            case 2: level = DifficultyLevel.INTERMEDIATE; break;
            case 3: level = DifficultyLevel.ADVANCED; break;
            default: System.out.println("Opção inválida!"); return;
        }
        
        List<Course> filteredCourses = reportService.getCoursesByDifficultyLevel(courses, level);
        System.out.println("\nCursos " + level + ":");
        filteredCourses.forEach(course -> System.out.println("- " + course.getTitle()));
    }
    
    private static void showUniqueInstructors() {
        System.out.println("\n=== INSTRUTORES ÚNICOS ===");
        List<String> instructors = reportService.getUniqueInstructors(courses);
        System.out.println("Instrutores ativos:");
        instructors.forEach(instructor -> System.out.println("- " + instructor));
    }
    
    private static void showStudentsByPlan() {
        System.out.println("\n=== ALUNOS POR PLANO ===");
        Map<String, List<Student>> studentsByPlan = reportService.groupStudentsBySubscriptionPlan(students);
        
        studentsByPlan.forEach((plan, studentList) -> {
            System.out.println("\nPlano: " + plan);
            studentList.forEach(student -> System.out.println("- " + student.getName()));
        });
    }
    
    private static void showAverageProgress() {
        System.out.println("\n=== MÉDIA GERAL DE PROGRESSO ===");
        double average = reportService.getAverageProgress(students);
        System.out.printf("Média geral: %.2f%%\n", average);
    }
    
    private static void showStudentWithMostEnrollments() {
        System.out.println("\n=== ALUNO COM MAIS MATRÍCULAS ===");
        Optional<Student> topStudent = reportService.getStudentWithMostEnrollments(students);
        
        if (topStudent.isPresent()) {
            Student student = topStudent.get();
            System.out.println("Aluno: " + student.getName());
            System.out.println("Matrículas ativas: " + student.getActiveEnrollmentsCount());
        } else {
            System.out.println("Nenhum aluno encontrado!");
        }
    }
    
    private static void exportData() {
        System.out.println("\n=== EXPORTAR DADOS ===");
        System.out.println("1. Exportar Cursos");
        System.out.println("2. Exportar Alunos");
        System.out.println("3. Exportar Matrículas");
        System.out.print("Escolha o tipo de dados: ");
        
        int choice = getIntInput();
        
        switch (choice) {
            case 1:
                exportCourses();
                break;
            case 2:
                exportStudents();
                break;
            case 3:
                exportEnrollments();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private static void exportCourses() {
        System.out.println("\n=== EXPORTAR CURSOS ===");
        System.out.println("Colunas disponíveis: title, description, instructorName, durationInHours, difficultyLevel, status");
        System.out.print("Digite as colunas separadas por vírgula (ou deixe vazio para todas): ");
        
        String columnsInput = scanner.nextLine().trim();
        List<String> selectedColumns;
        
        if (columnsInput.isEmpty()) {
            selectedColumns = Arrays.asList("title", "description", "instructorName", "durationInHours", "difficultyLevel", "status");
        } else {
            selectedColumns = Arrays.asList(columnsInput.split(","));
        }
        
        String csv = GenericCsvExporter.exportToCsv(courses, selectedColumns);
        System.out.println("\n=== CSV EXPORTADO ===");
        System.out.println(csv);
    }
    
    private static void exportStudents() {
        System.out.println("\n=== EXPORTAR ALUNOS ===");
        System.out.println("Colunas disponíveis: name, email, subscriptionPlan");
        System.out.print("Digite as colunas separadas por vírgula (ou deixe vazio para todas): ");
        
        String columnsInput = scanner.nextLine().trim();
        List<String> selectedColumns;
        
        if (columnsInput.isEmpty()) {
            selectedColumns = Arrays.asList("name", "email", "subscriptionPlan");
        } else {
            selectedColumns = Arrays.asList(columnsInput.split(","));
        }
        
        String csv = GenericCsvExporter.exportToCsv(students, selectedColumns);
        System.out.println("\n=== CSV EXPORTADO ===");
        System.out.println(csv);
    }
    
    private static void exportEnrollments() {
        System.out.println("\n=== EXPORTAR MATRÍCULAS ===");
        List<Enrollment> allEnrollments = students.stream()
                .flatMap(student -> student.getEnrollments().stream())
                .collect(Collectors.toList());
        
        if (allEnrollments.isEmpty()) {
            System.out.println("Não há matrículas para exportar!");
            return;
        }
        
        System.out.println("Colunas disponíveis: student, course, progress, enrollmentDate");
        System.out.print("Digite as colunas separadas por vírgula (ou deixe vazio para todas): ");
        
        String columnsInput = scanner.nextLine().trim();
        List<String> selectedColumns;
        
        if (columnsInput.isEmpty()) {
            selectedColumns = Arrays.asList("student", "course", "progress", "enrollmentDate");
        } else {
            selectedColumns = Arrays.asList(columnsInput.split(","));
        }
        
        String csv = GenericCsvExporter.exportToCsv(allEnrollments, selectedColumns);
        System.out.println("\n=== CSV EXPORTADO ===");
        System.out.println(csv);
    }
    
    private static void enrollInCourse() {
        System.out.println("\n=== MATRICULAR-SE EM CURSO ===");
        showCourseCatalog();
        
        System.out.print("Digite o título do curso: ");
        String title = scanner.nextLine().trim();
        
        try {
            Course course = courseService.findCourseByTitle(courses, title);
            Student student = (Student) currentUser;
            
            enrollmentService.enrollStudentInCourse(student, course);
            System.out.println("Matrícula realizada com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static void showEnrollments() {
        System.out.println("\n=== SUAS MATRÍCULAS ===");
        Student student = (Student) currentUser;
        List<Enrollment> enrollments = enrollmentService.getStudentEnrollments(student);
        
        if (enrollments.isEmpty()) {
            System.out.println("Você não está matriculado em nenhum curso.");
        } else {
            enrollments.forEach(enrollment -> {
                System.out.println("Curso: " + enrollment.getCourse().getTitle());
                System.out.println("Progresso: " + enrollment.getProgress() + "%");
                System.out.println("Data de matrícula: " + enrollment.getEnrollmentDate());
                System.out.println("---");
            });
        }
    }
    
    private static void updateProgress() {
        System.out.println("\n=== ATUALIZAR PROGRESSO ===");
        Student student = (Student) currentUser;
        List<Enrollment> enrollments = enrollmentService.getStudentEnrollments(student);
        
        if (enrollments.isEmpty()) {
            System.out.println("Você não está matriculado em nenhum curso.");
            return;
        }
        
        System.out.println("Seus cursos:");
        for (int i = 0; i < enrollments.size(); i++) {
            Enrollment enrollment = enrollments.get(i);
            System.out.println((i + 1) + ". " + enrollment.getCourse().getTitle() + 
                              " - " + enrollment.getProgress() + "%");
        }
        
        System.out.print("Escolha o curso (número): ");
        int courseIndex = getIntInput() - 1;
        
        if (courseIndex >= 0 && courseIndex < enrollments.size()) {
            System.out.print("Novo progresso (0-100%): ");
            int newProgress = getIntInput();
            
            try {
                Enrollment enrollment = enrollments.get(courseIndex);
                enrollmentService.updateProgress(student, enrollment.getCourse(), newProgress);
                System.out.println("Progresso atualizado com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
    
    private static void cancelEnrollment() {
        System.out.println("\n=== CANCELAR MATRÍCULA ===");
        Student student = (Student) currentUser;
        List<Enrollment> enrollments = enrollmentService.getStudentEnrollments(student);
        
        if (enrollments.isEmpty()) {
            System.out.println("Você não está matriculado em nenhum curso.");
            return;
        }
        
        System.out.println("Suas matrículas:");
        for (int i = 0; i < enrollments.size(); i++) {
            Enrollment enrollment = enrollments.get(i);
            System.out.println((i + 1) + ". " + enrollment.getCourse().getTitle() + 
                              " - " + enrollment.getProgress() + "%");
        }
        
        System.out.print("Escolha a matrícula para cancelar (número): ");
        int enrollmentIndex = getIntInput() - 1;
        
        if (enrollmentIndex >= 0 && enrollmentIndex < enrollments.size()) {
            try {
                Enrollment enrollment = enrollments.get(enrollmentIndex);
                enrollmentService.cancelEnrollment(student, enrollment.getCourse());
                System.out.println("Matrícula cancelada com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
    
    private static void showCourseCatalog() {
        System.out.println("\n=== CATÁLOGO DE CURSOS ===");
        List<Course> activeCourses = courseService.getActiveCourses(courses);
        
        if (activeCourses.isEmpty()) {
            System.out.println("Não há cursos ativos disponíveis.");
        } else {
            activeCourses.forEach(course -> {
                System.out.println("Título: " + course.getTitle());
                System.out.println("Descrição: " + course.getDescription());
                System.out.println("Instrutor: " + course.getInstructorName());
                System.out.println("Duração: " + course.getDurationInHours() + " horas");
                System.out.println("Nível: " + course.getDifficultyLevel());
                System.out.println("---");
            });
        }
    }
    
    private static void createSupportTicket() {
        System.out.println("\n=== ABRIR TICKET DE SUPORTE ===");
        
        if (currentUser == null) {
            System.out.println("Você precisa estar logado para abrir um ticket!");
            return;
        }
        
        System.out.print("Título do ticket: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Mensagem: ");
        String message = scanner.nextLine().trim();
        
        supportService.createTicket(title, message, currentUser);
        System.out.println("Ticket criado com sucesso! Número de tickets pendentes: " + 
                          supportService.getPendingTicketsCount());
    }
    
    private static void logout() {
        System.out.println("Logout realizado com sucesso!");
        currentUser = null;
    }
    
    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Por favor, digite um número válido: ");
            }
        }
    }
}
