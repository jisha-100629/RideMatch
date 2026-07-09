package com.example.RideMatch.algo;

import com.example.RideMatch.model.Edge;
import java.util.*;

public class RoutingEngine {
    
    public static List<Long> findShortestPath(Long source, Long target, List<Edge> allEdges) {
        // Step 1: Build the Adjacency List dynamically
        Map<Long, List<Edge>> graph = new HashMap<>();
        for (Edge edge : allEdges) {
            graph.putIfAbsent(edge.getSourceNodeId(), new ArrayList<>());
            graph.get(edge.getSourceNodeId()).add(edge);
        }

        Map<Long, Double> distances = new HashMap<>();
        Map<Long, Long> predecessors = new HashMap<>();
        PriorityQueue<VertexRoute> minHeap = new PriorityQueue<>();

        // Initialize source
        distances.put(source, 0.0);
        minHeap.add(new VertexRoute(source, 0.0));

        // Step 2: Process Dijkstra's Algorithm
        while (!minHeap.isEmpty()) {
            VertexRoute current = minHeap.poll();
            Long u = current.getNodeId();

            if (u.equals(target)) break;
            if (graph.get(u) == null) continue;

            for (Edge edge : graph.get(u)) {
                Long v = edge.getTargetNodeId();
                double weight = edge.getWeight();
                double newDist = distances.get(u) + weight;

                if (newDist < distances.getOrDefault(v, Double.MAX_VALUE)) {
                    distances.put(v, newDist);
                    predecessors.put(v, u);
                    minHeap.add(new VertexRoute(v, newDist));
                }
            }
        }

        // Step 3: Reconstruct path from target to source
        LinkedList<Long> path = new LinkedList<>();
        Long curr = target;
        if (predecessors.get(curr) == null && !curr.equals(source)) {
            return Collections.emptyList(); // Dest unreachable
        }
        while (curr != null) {
            path.addFirst(curr);
            curr = predecessors.get(curr);
        }
        return path;
    }
}