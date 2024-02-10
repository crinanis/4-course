package com.example.lab6;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "url1", urlPatterns = "/url1")
public class Url1 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String param = req.getParameter("urln");
        PrintWriter printWriter = resp.getWriter();

        if (param.equals("1")) {
            resp.sendRedirect(getServletContext().getInitParameter("URL1"));
        } else if (param.equals("2")) {
            resp.sendRedirect(getServletContext().getInitParameter("URL2"));
        } else {
            printWriter.println("parameter URL" + param + "not found");
        }
    }
}
