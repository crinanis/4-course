package com.example.lab11;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "sss_xml", value = "/sss_xml")
public class Sss_Xml extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int n = Integer.parseInt(req.getHeader("XRand-N"));

        Random random = new Random();
        int length = random.nextInt(6) + 5;

        StringBuilder res = new StringBuilder("<?xml version=\"1.0\" encoding = \"utf-8\" ?><rand>");

        for (int i = 0; i < length; ++i) {
            int value = random.nextInt(2 * n + 1) - n;
            res.append("<num>").append(value).append("</num>");
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("application/xml");
        resp.getWriter().println(res + "</rand>");
    }
}
