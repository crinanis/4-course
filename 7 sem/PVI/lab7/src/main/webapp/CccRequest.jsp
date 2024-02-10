<%@ page import="com.example.lab7.beans.CBean" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Request</title>
</head>
<body>
<%
    CBean cBean = (CBean) request.getAttribute("atrCBean");
    if (cBean != null) {
        out.write(request.getAttribute("atrCBean").toString() + "<br>");
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
