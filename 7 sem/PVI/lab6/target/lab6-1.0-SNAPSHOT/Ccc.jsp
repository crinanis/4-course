<%@ page import="com.example.lab6.beans.CBean" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>lab6</title>
</head>
<body>
<%
    ServletContext servletContext = (ServletContext) application;
    out.write("old: " + servletContext.getAttribute("atrCBean") + "<br>");
    CBean cBean = (CBean) servletContext.getAttribute("atrCBean");
    if (cBean != null) {
        String value1 = cBean.getValue1();
        String value2 = cBean.getValue2();
        String value3 = cBean.getValue3();
        if (value1 == null || value2 == null || value3 == null){
            out.write("Some values are empty");
        } else {
            out.println(
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
