<%@ page import="com.example.lab7.beans.CBean" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Session</title>
</head>
<body>
<%
    out.write("session id = " + session.getId() + "<br/>");
    System.out.println("in view: " + request.getSession().getId());
    CBean cBean = (CBean) session.getAttribute(request.getSession().getId());
    if (cBean != null) {
        String value1 = cBean.getValue1();
        String value2 = cBean.getValue2();
        String value3 = cBean.getValue3();
        if (value1 == null || value2 == null || value3 == null) {
            out.write("Some values are empty");
        } else {
            out.write(
                    "<br>Value1: " + value1 +
                            "<br>Value2: " + value2 +
                            "<br>Value3: " + value3
            );
        }
    } else {
        out.write("CBean is null");
    }
%>
</body>
</html>
