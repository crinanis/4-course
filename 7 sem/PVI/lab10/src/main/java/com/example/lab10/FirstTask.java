package com.example.lab10;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/staticRequest")
public class FirstTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        try {
            Connection connection = DbConnection.GetConnection();

            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery("select * from users");

            while (result.next()) {
                writer.println("<br/>" + result.getInt(1) + " " + result.getString(2) + " " +
                                result.getString(3) + " " + result.getString(4));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            writer.close();
        }
    }
}
