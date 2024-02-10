<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>lab10</title>
</head>
<body>
<a href="http://localhost:8080/lab10_war_exploded/staticRequest">Select * from users</a> <br/><br/>
<a href="http://localhost:8080/lab10_war_exploded/dynamicRequest?id=2">Select user with id</a> <br/><br/>
<form action="/lab10_war_exploded/procedure" method="GET">
    <input type="text" name="name" placeholder="name"/><br/>
    <input type="text" name="password" placeholder="password"/><br/>
    <input type="text" name="email" placeholder="email"/><br/>
    <button type="submit">Add</button>
</form>
</body>
</html>
