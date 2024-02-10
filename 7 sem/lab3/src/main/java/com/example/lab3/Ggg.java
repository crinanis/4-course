package com.example.lab3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Ggg", urlPatterns = "/Ggg")
public class Ggg extends HttpServlet {

    public Ggg() {
        super();
        System.out.println("Ggg:constructor");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Ggg:init");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Ggg:destroy");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        System.out.println("Ggg:service");

        RequestDispatcher requestDispatcher = null;
        String parm = req.getParameter("parm");

        if (parm != null) {
            if (parm.equalsIgnoreCase("GetTwoHtmlForward")) {
                requestDispatcher = req.getRequestDispatcher("redirect.html");
                requestDispatcher.forward(req, resp);
            } else if (parm.equalsIgnoreCase("GetTwoHtmlRedirect")) {
                resp.sendRedirect("redirect.html");
            } else if (parm.equalsIgnoreCase("HttpClientGet")) {
                resp.getWriter().println("Hello " + req.getParameter("name"));
            } else if (parm.equalsIgnoreCase("HttpClientPost")) {
                resp.getWriter().println("Hello " + req.getParameter("name"));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("Ggg:doGet " + req.getParameter("parm"));
        System.out.println("Ggg:doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("Ggg:doPost  " + req.getParameter("parm"));
        System.out.println("Ggg:doPost");
    }
}

