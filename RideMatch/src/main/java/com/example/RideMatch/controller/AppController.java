package com.example.RideMatch.controller;

import com.example.RideMatch.model.Driver;
import com.example.RideMatch.model.Edge;
import com.example.RideMatch.model.Node;
import com.example.RideMatch.repository.DriverRepository;
import com.example.RideMatch.repository.EdgeRepository;
import com.example.RideMatch.repository.NodeRepository;
import com.example.RideMatch.service.RideService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AppController {
    private final NodeRepository nodeRepository;
    private final EdgeRepository edgeRepository;
    private final DriverRepository driverRepository;
    private final RideService rideService;

    public AppController(NodeRepository nodeRepository, EdgeRepository edgeRepository, 
                         DriverRepository driverRepository, RideService rideService) {
        this.nodeRepository = nodeRepository;
        this.edgeRepository = edgeRepository;
        this.driverRepository = driverRepository;
        this.rideService = rideService;
    }

    @PostConstruct
    public void seedMockData() {
        // Construct the 3x3 topological graph network
        nodeRepository.save(new Node(1L, "Intersection 1", 0, 0));
        nodeRepository.save(new Node(2L, "Intersection 2", 1, 0));
        nodeRepository.save(new Node(3L, "Intersection 3", 2, 0));
        nodeRepository.save(new Node(4L, "Intersection 4", 0, 1));
        nodeRepository.save(new Node(5L, "Intersection 5", 1, 1));
        nodeRepository.save(new Node(6L, "Intersection 6", 2, 1));

        // Connect nodes via paths
        edgeRepository.save(new Edge(1L, 2L, 1.0));
        edgeRepository.save(new Edge(2L, 1L, 1.0));
        edgeRepository.save(new Edge(2L, 3L, 1.2));
        edgeRepository.save(new Edge(1L, 4L, 2.0));
        edgeRepository.save(new Edge(4L, 5L, 1.0));
        edgeRepository.save(new Edge(5L, 4L, 1.0));
        edgeRepository.save(new Edge(5L, 6L, 1.1));
        edgeRepository.save(new Edge(2L, 5L, 1.5));
        edgeRepository.save(new Edge(3L, 6L, 2.5));

        // Plop our sample drivers on their start points
        driverRepository.save(new Driver("Driver Swift (Node 1)", 1L, "AVAILABLE"));
        driverRepository.save(new Driver("Driver Blitz (Node 4)", 4L, "AVAILABLE"));
    }

    @GetMapping("/")
    public String showDashboard() {
        return "dashboard";
    }

    @PostMapping("/api/ride/book")
    @ResponseBody
    public Map<String, Object> processBooking(@RequestParam Long pickup, @RequestParam Long drop) {
        return rideService.matchAndRoute(pickup, drop);
    }
}