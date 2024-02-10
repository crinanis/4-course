package com.example.lab11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "sss_json", value = "/sss_json")
public class Sss_Json extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int n = Integer.parseInt(req.getHeader("XRand-N"));

        Random random = new Random();
        int length = random.nextInt(6) + 5;

        List<Integer> set = new ArrayList<>();

        for (int i = 0; i < length; ++i) {
            int value = random.nextInt(2 * n + 1) - n;
            set.add(value);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(set);
        resp.setContentType("application/json");
        resp.getWriter().println("{\"num\":" + set + "}");
    }
}
