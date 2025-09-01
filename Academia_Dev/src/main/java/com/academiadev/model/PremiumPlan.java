package com.academiadev.model;

public class PremiumPlan implements SubscriptionPlan {
    
    @Override
    public String getPlanName() {
        return "Premium";
    }
    
    @Override
    public int getMaxEnrollments() {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public boolean canEnroll(int currentEnrollments) {
        return true; // Sempre pode se matricular
    }
    
    @Override
    public String toString() {
        return "PremiumPlan{unlimited enrollments}";
    }
}
