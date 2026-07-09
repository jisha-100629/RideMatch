<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>DSA Engine Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f4f7f6; }
        .grid-container { display: grid; grid-template-columns: repeat(3, 120px); gap: 20px; margin-top: 20px; }
        .node { width: 120px; height: 80px; background: #fff; border: 2px solid #ccc; 
                display: flex; flex-direction: column; align-items: center; justify-content: center; 
                border-radius: 8px; font-weight: bold; font-size: 13px; cursor: pointer;}
        .highlighted { background: #ffeb3b !important; border-color: #f57f17 !important; }
        .btn { padding: 10px 20px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 15px;}
        .panel { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); margin-bottom: 20px;}
    </style>
</head>
<body>

    <div class="panel">
        <h2>Custom Routing Engine Optimization</h2>
        <p>Select pickup and drop-off nodes from the grid array below to compute routing trajectories via custom Dijkstra.</p>
        <label>Pickup Node ID: </label><input type="number" id="pickupInput" min="1" max="6" value="1">
        <label>Destination Node ID: </label><input type="number" id="dropInput" min="1" max="6" value="6">
        <button class="btn" onclick="requestRide()">Find Fast Route and Driver</button>
    </div>

    <div class="panel">
        <h3>Live Processing Terminal View</h3>
        <p id="outputBox">Terminal Status: Standby...</p>
    </div>

    <div class="grid-container">
        <div class="node" id="node-1">Node 1<br>(0,0)</div>
        <div class="node" id="node-2">Node 2<br>(1,0)</div>
        <div class="node" id="node-3">Node 3<br>(2,0)</div>
        <div class="node" id="node-4">Node 4<br>(0,1)</div>
        <div class="node" id="node-5">Node 5<br>(1,1)</div>
        <div class="node" id="node-6">Node 6<br>(2,1)</div>
    </div>

    <script>
        function requestRide() {
            const pickup = document.getElementById("pickupInput").value;
            const drop = document.getElementById("dropInput").value;
            
            // Clear prior routing visuals
            document.querySelectorAll('.node').forEach(el => el.classList.remove('highlighted'));

            // Build request parameters
            const params = new URLSearchParams();
            params.append('pickup', pickup);
            params.append('drop', drop);

            fetch('/api/ride/book', {
                method: 'POST',
                body: params
            })
            .then(res => res.json())
            .then(data => {
                document.getElementById("outputBox").innerHTML = 
                    `<strong>Matched Driver:</strong> \${data.driverName} <br> <strong>Computed Node Path:</strong> \${data.route.join(" ➔ ")}`;
                
                // Highlight the path elements in the grid layout dynamically
                data.route.forEach(nodeId => {
                    const nodeEl = document.getElementById(`node-\${nodeId}`);
                    if(nodeEl) nodeEl.classList.add('highlighted');
                });
            })
            .catch(err => {
                console.error(err);
                alert("Error running background calculations.");
            });
        }
    </script>
</body>
</html>