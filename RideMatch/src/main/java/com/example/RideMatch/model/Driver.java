package com.example.RideMatch.model;

import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long currentNodeId;
    private String status; // AVAILABLE, BUSY

    public Driver() {}
    public Driver(String name, Long currentNodeId, String status) {
        this.name = name; this.currentNodeId = currentNodeId; this.status = status;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Long getCurrentNodeId() { return currentNodeId; }
    public String getStatus() { return status; }
    public void setCurrentNodeId(Long currentNodeId) { this.currentNodeId = currentNodeId; }
    public void setStatus(String status) { this.status = status; }
}
