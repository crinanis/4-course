package com.example.lab8;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/Bbb")
public class Bbb extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("BbbHeader1", "Header 1 from Bbb");
        response.addHeader("BbbHeader2", "Header 2 from Bbb");

        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        pw.print("<h3>Query Params from request:</h3>");
        for (Map.Entry<String, String> entry : getParametersMap(request).entrySet()) {
            pw.println(entry.getKey() + "= " + entry.getValue() + "<br/>");
        }

        pw.print("<h3>Headers from request:</h3>");
        for (Map.Entry<String, String> entry : getHeadersMap(request).entrySet()) {
            pw.println(entry.getKey() + "= " + entry.getValue() + "<br/>");
        }

        pw.close();
    }

    private Map<String, String> getParametersMap(HttpServletRequest request) {
        Map<String, String> parametersMap = new HashMap<>();
        Enumeration<String> params = request.getParameterNames(); //перечисление всех имён
        while (params.hasMoreElements()) {
            String key = params.nextElement();
            parametersMap.put(key, request.getParameter(key));
        }
        return parametersMap;
    }

    private Map<String, String> getHeadersMap(HttpServletRequest request) {
        Map<String, String> headersMap = new HashMap<>();
        Enumeration<String> enh = request.getHeaderNames();
        while (enh.hasMoreElements()) {
            String key = enh.nextElement();
            headersMap.put(key, request.getHeader(key));
        }
        return headersMap;
    }
}
