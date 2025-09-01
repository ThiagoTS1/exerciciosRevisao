package com.academiadev.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class SupportTicket {
    private String title;
    private String message;
    private User user;
    private LocalDateTime creationDate;
    private boolean processed;
    
    public SupportTicket(String title, String message, User user) {
        this.title = title;
        this.message = message;
        this.user = user;
        this.creationDate = LocalDateTime.now();
        this.processed = false;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getMessage() {
        return message;
    }
    
    public User getUser() {
        return user;
    }
    
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    
    public boolean isProcessed() {
        return processed;
    }
    
    public void markAsProcessed() {
        this.processed = true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportTicket that = (SupportTicket) o;
        return Objects.equals(title, that.title) && 
               Objects.equals(user, that.user) && 
               Objects.equals(creationDate, that.creationDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, user, creationDate);
    }
    
    @Override
    public String toString() {
        return "SupportTicket{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", user=" + user.getEmail() +
                ", creationDate=" + creationDate +
                ", processed=" + processed +
                '}';
    }
}
