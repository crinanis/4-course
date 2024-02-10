<%@ page import="java.io.BufferedWriter" %>
<%@ page import="java.io.FileWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
evening.jsp
<%
    BufferedWriter eveningWriter = new BufferedWriter(new FileWriter("D:\\1POIT\\4\\PVI\\lab4\\src\\main\\logs\\stdout.txt"));
    eveningWriter.append("logs: Evening");
    eveningWriter.append("\n");
    eveningWriter.close();
%>
</body>
</html>
