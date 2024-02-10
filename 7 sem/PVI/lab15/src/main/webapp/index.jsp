<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>lab15</title>
</head>
<body>
<div class="wrapper">
    <div>
        <button onclick="socketOpen()">Open</button>
        <button onclick="socketClose()">Close</button>
    </div>
    <div class="result"></div>
</div>
<script>
    let socket;
    let result;

    function socketOpen() {
        socket = new WebSocket("ws://localhost:8080/lab15_war_exploded/websocket");
        result = document.querySelector('.result');
        console.log("Result: ", result);

        socket.onmessage = (event) => {
            console.log("Message: ", event.data);
            result.innerHTML = event.data;
        }

        socket.onopen = () => {
            socket.send("Hello");
            console.log("Socket was opened");
        }

        socket.onclose = () => {
            console.log("Socket was closed");
        }
    }

    function socketClose() {
        socket.close();
    }
</script>
</body>
</html>