package com.example.lab13;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SssServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String directory = getServletContext().getInitParameter("doc-dir");
        System.out.println(directory);
        String filename = req.getParameter("file");
        File file = new File(directory + "\\" + filename);

        resp.setContentType("application/msword");
        resp.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
        resp.setContentLength((int) file.length());

        System.out.println("download file: " + file.getPath());

        FileInputStream in = new FileInputStream(file);
        ServletOutputStream out = resp.getOutputStream();
        int readBytes;
        while ((readBytes = in.read()) != -1) {
            out.write(readBytes);
        }
    }
}
