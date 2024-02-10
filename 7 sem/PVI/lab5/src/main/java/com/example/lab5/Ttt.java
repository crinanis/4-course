package com.example.lab5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/Ttt")
public class Ttt extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Service");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String sex = req.getParameter("sex");

        PrintWriter printWriter = res.getWriter();
        printWriter.println(
                "<br/>Firstname: " + firstname +
                        "<br/>Lastname: " + lastname +
                        "<br/>Sex: " + sex
        );
    }

    public Ttt() {
        super();
        System.out.println("Constructor");
    }
}
