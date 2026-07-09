package com.example.RideMatch.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nodes")
public class Node {
    @Id
    private Long id;
    private String name;
    private int x;
    private int y;

    public Node() {}
    public Node(Long id, String name, int x, int y) {
        this.id = id; this.name = name; this.x = x; this.y = y;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getX() { return x; }
    public int getY() { return y; }
}