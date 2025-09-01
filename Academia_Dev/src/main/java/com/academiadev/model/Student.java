package com.academiadev.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private SubscriptionPlan subscriptionPlan;
    private List<Enrollment> enrollments;
    
    public Student(String name, String email, SubscriptionPlan subscriptionPlan) {
        super(name, email);
        this.subscriptionPlan = subscriptionPlan;
        this.enrollments = new ArrayList<>();
    }
    
    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }
    
    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }
    
    public List<Enrollment> getEnrollments() {
        return new ArrayList<>(enrollments);
    }
    
    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }
    
    public void removeEnrollment(Enrollment enrollment) {
        this.enrollments.remove(enrollment);
    }
    
    public int getActiveEnrollmentsCount() {
        return (int) enrollments.stream()
                .filter(e -> e.getCourse().getStatus() == CourseStatus.ACTIVE)
                .count();
    }
    
    public boolean canEnrollInNewCourse() {
        return subscriptionPlan.canEnroll(getActiveEnrollmentsCount());
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", subscriptionPlan=" + subscriptionPlan.getPlanName() +
                ", activeEnrollments=" + getActiveEnrollmentsCount() +
                '}';
    }
}
