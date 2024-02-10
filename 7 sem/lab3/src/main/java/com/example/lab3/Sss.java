package com.example.lab3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

@WebServlet(name = "Sss", urlPatterns = "/Sss")
public class Sss extends HttpServlet {

    public Sss() {
        super();
        System.out.println("Sss:constructor");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Sss:init");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Sss:destroy");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("Sss:service");

        RequestDispatcher requestDispatcher = null;
        String parm = req.getParameter("parm");
        PrintWriter printWriter = resp.getWriter();

        if (parm != null) {
            if (parm.equalsIgnoreCase("GetGggForward")) {
                requestDispatcher = req.getRequestDispatcher("Ggg");
                requestDispatcher.forward(req, resp);
            } else if (parm.equalsIgnoreCase("GetGggRedirect")) {
                resp.sendRedirect("http://192.168.23.128:8080/lab3/Ggg");
            } else if (parm.equalsIgnoreCase("PostGggForward"))                   // Task2
            {
                requestDispatcher = req.getRequestDispatcher("Ggg");
                requestDispatcher.forward(req, resp);
            } else if (parm.equalsIgnoreCase("PostGggRedirect")) {
                resp.sendRedirect("http://192.168.23.128:8080/lab3/Ggg");
            } else if (parm.equalsIgnoreCase("GetHtmlForward"))                   // Task3
            {
                requestDispatcher = req.getRequestDispatcher("redirect.html");
                requestDispatcher.forward(req, resp);
            } else if (parm.equalsIgnoreCase("GetHtmlRedirect")) {
                resp.sendRedirect("http://192.168.23.128:8080/lab3/redirect.html");
            } else if (parm.equalsIgnoreCase("GetTwoHtmlForward"))                // Task4
            {
                requestDispatcher = req.getRequestDispatcher("Ggg");
                requestDispatcher.forward(req, resp);
            } else if (parm.equalsIgnoreCase("GetTwoHtmlRedirect")) {
                resp.sendRedirect("http://192.168.23.128:8080/lab3/Ggg?parm=GetTwoHtmlRedirect");
            } else if (parm.equalsIgnoreCase("GetTwoInfoForward"))           // Task 5
            {
                printWriter.write("Info from Sss");
                requestDispatcher = req.getRequestDispatcher("Ggg");
                requestDispatcher.forward(req, resp);
            } else if (parm.equalsIgnoreCase("GetTwoInfoRedirect")) {
                printWriter.write("Info from Sss");
                resp.sendRedirect("http://192.168.23.128:8080/lab3/Ggg");
            } else if (parm.equalsIgnoreCase("HttpClientGet")) {                // Task 7
                HttpClient httpclient = HttpClients.createDefault();
                HttpGet httpget = new HttpGet("http://192.168.23.128:8080/lab3/Ggg?parm=HttpClientGet&name=ksu");
                HttpResponse httpresponse = httpclient.execute(httpget);

                Scanner sc = new Scanner(httpresponse.getEntity().getContent());
                System.out.println(httpresponse.getStatusLine());
                while (sc.hasNext()) {
                    System.out.println(sc.nextLine());
                }
            } else if (parm.equalsIgnoreCase("HttpClientPost")) {
                HttpClient httpclient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost("http://192.168.23.128:8080/lab3/Ggg?parm=HttpClientPost&name=ksu");
                HttpResponse httpResponsePost = httpclient.execute(httpPost);

                Scanner scc = new Scanner(httpResponsePost.getEntity().getContent());
                System.out.println(httpResponsePost.getStatusLine());
                while (scc.hasNext()) {
                    System.out.println(scc.nextLine());
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("Sss:doGet");
        System.out.println("Sss:doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("Sss:doPost");
        System.out.println("Sss:doPost");
    }
}