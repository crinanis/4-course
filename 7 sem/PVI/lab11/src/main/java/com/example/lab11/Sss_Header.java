package com.example.lab11;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "sss_header", value = "/sss_header")
public class Sss_Header extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String x = req.getHeader("Value-X");
        String y = req.getHeader("Value-Y");

        int z = Integer.parseInt(x) + Integer.parseInt(y);
        try {
            Thread.sleep(10000);  // 10 sec
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        resp.addHeader("Value-Z", String.valueOf(z));
        resp.getWriter().println("");
    }
}
