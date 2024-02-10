package com.example.lab4;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

@WebServlet(urlPatterns = "/Jjj")
public class Jjj extends HttpServlet {

    private Calendar calendar = Calendar.getInstance();
    private int hour = calendar.get(Calendar.HOUR_OF_DAY);

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Jjj:init");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Jjj:destroy");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Jjj:doGet");
        if (hour > 0 && hour <= 5) {
            req.getRequestDispatcher("night.jsp").forward(req, resp);
        } else if (hour > 5 && hour < 12) {
            req.getRequestDispatcher("morning.jsp").forward(req, resp);
        } else if (hour >= 12 && hour <= 17) {
            resp.sendRedirect("afternoon.jsp");
        } else {
            req.getRequestDispatcher("evening.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Jjj:doPost");
        if (hour > 0 && hour <= 5) {
            req.getRequestDispatcher("night.jsp").forward(req, resp);
        } else if (hour > 5 && hour < 12) {
            req.getRequestDispatcher("morning.jsp").forward(req, resp);
        } else if (hour >= 12 && hour <= 17) {
            req.getRequestDispatcher("afternoon.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("evening.jsp").forward(req, resp);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Jjj service " + req.getMethod());
        if (req.getMethod().contains("POST")) {
            this.doPost(req, resp);
        } else if (req.getMethod().contains("GET")) {
            this.doGet(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, "Send get or post request");
        }
    }
}
