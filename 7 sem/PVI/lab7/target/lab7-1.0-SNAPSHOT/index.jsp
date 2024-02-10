<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>lab7</title>
</head>
<body>
<h4>Request</h4>
<form action="/lab7_war_exploded/CccRequest" method="GET">
    <input type="text" name="value1" placeholder="Value1"/><br/>
    <input type="text" name="value2" placeholder="Value2"/><br/>
    <input type="text" name="value3" placeholder="Value3"/><br/>
    New <input type="radio" name="CBean" value="new" label="New" title="" checked/><br/>
    Old <input type="radio" name="CBean" value="old" label="Old" title=""/><br/>
    <button type="submit">Click</button>
</form>
<hr/>
<h4>Session</h4>
<form action="/lab7_war_exploded/CccSession" method="Get">
    <input type="text" name="value1" placeholder="Value1"/><br/>
    <input type="text" name="value2" placeholder="Value2"/><br/>
    <input type="text" name="value3" placeholder="Value3"/><br/>
    New <input type="radio" name="CBean" value="new" label="New" title="" checked/><br/>
    Old <input type="radio" name="CBean" value="old" label="Old" title=""/><br/>
    <button type="submit">Click</button>
</form>
</body>
</html>
