package com.example.RideMatch.algo;

public class VertexRoute implements Comparable<VertexRoute> {
    private final Long nodeId;
    private final double distance;

    public VertexRoute(Long nodeId, double distance) {
        this.nodeId = nodeId;
        this.distance = distance;
    }

    public Long getNodeId() { 
        return nodeId; 
    }

    public double getDistance() { 
        return distance; 
    }

    @Override
    public int compareTo(VertexRoute other) {
        return Double.compare(this.distance, other.distance);
    }
}