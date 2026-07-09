# RideMatch: High-Performance Ride-Matching & Routing Engine

RideMatch is a high-performance Java Full-Stack web application designed to solve core logistics, scheduling, and routing mechanics programmatically. Unlike basic CRUD clones that offload calculations to external mapping APIs, this engine features an entirely custom backend algorithmic core utilizing fundamental Data Structures and Algorithms (DSA).

The application simulates a 3×3 topological city grid, dynamically matching available drivers to passenger pickup coordinates using **Min-Heaps** and computing mathematically optimal traversal routes using a custom implementation of **Dijkstra's Algorithm**.

---

## 🛠️ Key Architectural Highlights & DSA Applications

- **Custom Graph Representation:** Built using an in-memory Adjacency List (`HashMap<Long, List<Edge>>`) to efficiently represent intersections (nodes) and roadways (edges) without relying on external graph libraries.
- **Shortest Path Computation:** Implements a complete custom version of **Dijkstra's Algorithm** to calculate the minimum-cost route between two locations.
- **Driver Dispatch using Min-Heap:** Uses Java's `PriorityQueue` as a **Min-Heap**, where custom objects implement the `Comparable` interface to efficiently identify the nearest available driver.
- **Layered Spring Boot Architecture:** Separates persistence, business logic, algorithms, and presentation layers for clean and maintainable code.
- **Dynamic Route Visualization:** Backend-generated routes are returned as JSON and rendered dynamically on the frontend using Vanilla JavaScript.

---

## ⚙️ Tech Stack

| Category | Technologies |
|----------|--------------|
| Language | Java 17 |
| Backend | Spring Boot, Spring Data JPA |
| Frontend | JSP, HTML, CSS, Vanilla JavaScript |
| Database | H2 Database |
| Build Tool | Maven |
| Algorithms | Dijkstra's Algorithm, Min-Heap (Priority Queue), Graph (Adjacency List) |

---

## 🏗️ Project Architecture

```text
RideMatch/
├── src/
│   ├── main/
│   │   ├── java/com/example/RideMatch/
│   │   │   ├── model/        # JPA Entity classes
│   │   │   ├── repository/   # Spring Data JPA repositories
│   │   │   ├── algo/         # Custom Dijkstra & Min-Heap implementations
│   │   │   ├── service/      # Business logic and routing engine
│   │   │   └── controller/   # REST endpoints & JSP controllers
│   │   └── webapp/
│   │       └── WEB-INF/
│   │           └── jsp/      # JSP views
│   └── resources/
│       ├── application.properties
│       └── data.sql
└── pom.xml
```

---

## ✨ Features

- Simulates a city road network using a graph data structure.
- Finds the nearest available driver using a Min-Heap.
- Computes the optimal travel path using Dijkstra's Algorithm.
- Automatically updates driver availability after assignment.
- Displays routes dynamically on the frontend.
- Stores driver and route information using an H2 in-memory database.
- Clean layered architecture following Spring Boot best practices.

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or 21
- Apache Maven

---

### Clone the Repository

```bash
git clone https://github.com/jisha-100629/RideMatch.git
cd RideMatch
```

---

### Run the Application

**Windows**

```bash
mvnw.cmd spring-boot:run
```

**Mac/Linux**

```bash
./mvnw spring-boot:run
```

---

### Open the Application

```
http://localhost:8080/
```

---

### H2 Database Console (Optional)

```
http://localhost:8080/h2-console
```

Use:

```
JDBC URL: jdbc:h2:mem:ridedb
Username: sa
Password:
```

---

## 📈 System Demonstration

When the application starts, it automatically creates a sample city graph and initializes available drivers.

### Initial Driver Locations

| Driver | Starting Node |
|---------|---------------|
| Swift | Node 1 |
| Blitz | Node 4 |

---

### Sample Ride Request

**Pickup:** Node 1

**Destination:** Node 6

---

### Ride Matching Process

1. The Min-Heap evaluates all available drivers.
2. Driver **Swift** is selected because it is already located at Node 1.
3. The routing engine executes Dijkstra's Algorithm.
4. The optimal route is calculated as:

```
1 → 2 → 5 → 6
```

Total path cost:

```
3.6
```

5. The driver's status changes from **AVAILABLE** to **BUSY**.
6. The calculated route is returned as JSON.
7. The frontend highlights the selected path dynamically.

---

## 🧠 Data Structures & Algorithms Used

| Data Structure / Algorithm | Purpose |
|----------------------------|---------|
| Graph (Adjacency List) | Represents the city road network |
| HashMap | Stores graph nodes and adjacency lists |
| ArrayList | Stores neighboring edges |
| PriorityQueue (Min-Heap) | Selects the nearest available driver |
| Comparable Interface | Maintains heap ordering |
| Dijkstra's Algorithm | Computes shortest path between locations |

---

## 📌 Future Enhancements

- Larger dynamic city maps
- Traffic-aware routing
- Multiple ride requests
- Driver ratings
- ETA estimation
- Real-time ride tracking
- Authentication and user accounts
- REST API documentation using Swagger/OpenAPI

---

## 👩‍💻 Author

**Jisha**

GitHub: https://github.com/jisha-100629
