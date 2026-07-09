package com.example.RideMatch.service;

import com.example.RideMatch.algo.RoutingEngine;
import com.example.RideMatch.algo.VertexRoute;
import com.example.RideMatch.model.Driver;
import com.example.RideMatch.model.Edge;
import com.example.RideMatch.repository.DriverRepository;
import com.example.RideMatch.repository.EdgeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RideService {
    private final DriverRepository driverRepository;
    private final EdgeRepository edgeRepository;

    public RideService(DriverRepository driverRepository, EdgeRepository edgeRepository) {
        this.driverRepository = driverRepository;
        this.edgeRepository = edgeRepository;
    }

    public Map<String, Object> matchAndRoute(Long pickupNodeId, Long destinationNodeId) {
        List<Edge> edges = edgeRepository.findAll();
        List<Driver> availableDrivers = driverRepository.findByStatus("AVAILABLE");
        
        PriorityQueue<VertexRoute> driverHeap = new PriorityQueue<>();

        // Use our algorithm to find how close drivers are to the rider
        for (Driver driver : availableDrivers) {
            List<Long> pathToRider = RoutingEngine.findShortestPath(driver.getCurrentNodeId(), pickupNodeId, edges);
            if (!pathToRider.isEmpty()) {
                double travelCost = pathToRider.size(); 
                driverHeap.add(new VertexRoute(driver.getId(), travelCost));
            }
        }

        Map<String, Object> response = new HashMap<>();
        Driver bestDriver = null;
        
        if (!driverHeap.isEmpty()) {
            VertexRoute closest = driverHeap.poll();
            bestDriver = driverRepository.findById(closest.getNodeId()).orElse(null);
            
            if (bestDriver != null) {
                bestDriver.setStatus("BUSY");
                driverRepository.save(bestDriver);
                response.put("driverName", bestDriver.getName());
            }
        } else {
            response.put("driverName", "No Available Drivers (Heap Empty)");
        }

        // Get passenger routing path
        List<Long> travelRoute = RoutingEngine.findShortestPath(pickupNodeId, destinationNodeId, edges);
        response.put("route", travelRoute);

        return response;
    }
}