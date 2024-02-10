<%@page %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Calendar" %>
<%!
    String name = "";
%>

<html>
<head>
    <title>Index</title>
</head>
<body>

<%
    Calendar calendar = Calendar.getInstance();
    int hours = calendar.get(Calendar.HOUR_OF_DAY);

    if (hours < 6) {
        out.println("Good night");
        name = "night.jsp";
    } else if (hours < 12) {
        out.println("Good morning");
        name = "morning.jsp";
    } else if (hours < 18) {
        out.println("Good afternoon");
        name = "afternoon.jsp";
    } else {
        out.println("Good  evening");
        name = "evening.jsp";
    }

    out.println("<br><br>");

    out.println("<table style=\"border: 1px solid black;\">");
    LocalDateTime localDateTime = LocalDateTime.now();
    for (int i = 0; i < 7; i++) {
        LocalDateTime date = localDateTime.plusDays(i);
        out.println("<tr>");
        out.println("<td>" + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</td>");
        out.println("<td>" + date.getDayOfWeek().getValue() + "</td>");
        out.println("</tr>");
    }
    out.println("</table>");
%>

<br>
<form>
    <input type="submit" name="press" value="<%=name%>"/>
</form

<%
    if (request.getParameter("press") != null) {
        switch (request.getParameter("press")) {
            case "night.jsp":
%>
<jsp:forward page="night.jsp"/>
<%
        break;
    case "morning.jsp":
%>
<%@ include file="morning.jsp" %>
<jsp:include page="morning.jsp"/>

<%
        break;
    case "afternoon.jsp":
%>
<%@ include file="afternoon.jsp" %>
<jsp:include page="afternoon.jsp"/>
<%
        break;
    case "evening.jsp":
%>
<%@ include file="evening.jsp" %>
<jsp:include page="evening.jsp"/>
<%
                break;
        }
    }
%>

<br>
<a href="http://192.168.23.128:8080/lab4/Afternoon">Afternoon servlet</a>
<br><br>
<a href="Jjj">Jjj servlet</a>
<br><br>
<form action="Jjj" method="post">
    <input type="submit" value="Jjj">
</form>
</body>
</html>
