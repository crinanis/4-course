<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>lab6</title>
</head>
<body>
<%
    out.println("URL1 " + application.getInitParameter("URL1"));
    out.println("<br/>");
    out.println("URL2 " + application.getInitParameter("URL2"));
%>
<h4>Get Url</h4>
<form action="/lab6_war_exploded/url1" method="GET">
    <input type="text" name="urln" placeholder="URLn">
    <button type="submit">click</button>
</form>
<hr/>
<h4>GET</h4>
<form action="/lab6_war_exploded/Ccc" method="get">
    <input type="text" name="value1" placeholder="Value1"/><br/>
    <input type="text" name="value2" placeholder="Value2"/><br/>
    <input type="text" name="value3" placeholder="Value3"/><br/>
    New <input type="radio" name="CBean" value="new" label="New" title="" checked/><br/>
    Old <input type="radio" name="CBean" value="old" label="Old" title=""/><br/>
    <button type="submit">Click</button>
</form>
</body>
</html>
