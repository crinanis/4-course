<%@ page import="com.example.lab13.ChoiceXXX" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>lab13</title>
</head>
<body>
<h1><%="lab13"%>
</h1>
<br/>
<%
    String d = config.getServletContext().getInitParameter("doc-dir");
    ChoiceXXX ch = new ChoiceXXX(d, "doc");
    String file;
    for (int i = 0; i < ch.listxxx.length; i++) {
        file = ch.listxxx[i];
%>
<br/>
<a href="/lab13_war_exploded/sss?file=<%=file%>"><%=file%>
</a>
<%}%>
</body>
</html>