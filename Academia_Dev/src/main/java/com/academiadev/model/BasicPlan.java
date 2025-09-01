package com.academiadev.model;

public class BasicPlan implements SubscriptionPlan {
    
    @Override
    public String getPlanName() {
        return "Basic";
    }
    
    @Override
    public int getMaxEnrollments() {
        return 3;
    }
    
    @Override
    public boolean canEnroll(int currentEnrollments) {
        return currentEnrollments < getMaxEnrollments();
    }
    
    @Override
    public String toString() {
        return "BasicPlan{maxEnrollments=" + getMaxEnrollments() + "}";
    }
}
