package com.academiadev.service;

import com.academiadev.model.SupportTicket;
import com.academiadev.model.User;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Optional;

public class SupportService {
    private Queue<SupportTicket> ticketQueue;
    
    public SupportService() {
        this.ticketQueue = new LinkedList<>();
    }
    
    public void createTicket(String title, String message, User user) {
        SupportTicket ticket = new SupportTicket(title, message, user);
        ticketQueue.offer(ticket);
    }
    
    public Optional<SupportTicket> getNextTicket() {
        SupportTicket ticket = ticketQueue.poll();
        if (ticket != null) {
            ticket.markAsProcessed();
        }
        return Optional.ofNullable(ticket);
    }
    
    public boolean hasPendingTickets() {
        return !ticketQueue.isEmpty();
    }
    
    public int getPendingTicketsCount() {
        return ticketQueue.size();
    }
    
    public Queue<SupportTicket> getTicketQueue() {
        return new LinkedList<>(ticketQueue);
    }
}
