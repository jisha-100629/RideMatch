package com.example.RideMatch.model;

import jakarta.persistence.*;
 
@Entity
@Table(name = "edges")
public class Edge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long sourceNodeId;
    private Long targetNodeId;
    private double weight;

    public Edge() {}
    public Edge(Long sourceNodeId, Long targetNodeId, double weight) {
        this.sourceNodeId = sourceNodeId;
        this.targetNodeId = targetNodeId;
        this.weight = weight;
    }

    public Long getSourceNodeId() { return sourceNodeId; }
    public Long getTargetNodeId() { return targetNodeId; }
    public double getWeight() { return weight; }
}
