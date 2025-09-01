package com.academiadev.model;

public interface SubscriptionPlan {
    String getPlanName();
    int getMaxEnrollments();
    boolean canEnroll(int currentEnrollments);
}
