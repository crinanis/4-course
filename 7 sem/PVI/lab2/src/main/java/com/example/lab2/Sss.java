package com.example.lab2;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sss extends HttpServlet {
    public Sss() {
        super();
        System.out.println("Sss Constructor");
    }

    @Override
    public void init(ServletConfig sc) throws ServletException {
        super.init();
        System.out.println("Sss Servlet initialized");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("Sss Service");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Sss Servlet destroyed");
    }

    private void getValues(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String methodCalled = req.getMethod();
        String serverName = req.getServerName();
        String remoteAddr = req.getRemoteAddr();
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");

        resp.setContentType("text/html");
        resp.getWriter().println("Method: " + methodCalled + "<br><br>");
        resp.getWriter().println("First Name: " + firstName + "<br>");
        resp.getWriter().println("Last Name: " + lastName + "<br><br>");
        resp.getWriter().println("Server Name: " + serverName + "<br>");
        resp.getWriter().println("Remote IP Address: " + remoteAddr + "<br><br>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getValues(req, resp);

        String queryString = req.getQueryString();
        resp.getWriter().println("GET Request: " + queryString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getValues(req, resp);
    }
}
