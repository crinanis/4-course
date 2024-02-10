<%@ page import="java.io.BufferedWriter" %>
<%@ page import="java.io.FileWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
afternoon.jsp
<%
    BufferedWriter afternoonWriter = new BufferedWriter(new FileWriter("D:\\1POIT\\4\\PVI\\lab4\\src\\main\\logs\\stdout.txt"));
    afternoonWriter.append("logs: Afternoon");
    afternoonWriter.append("\n");
    afternoonWriter.close();
%>
</body>
</html>
