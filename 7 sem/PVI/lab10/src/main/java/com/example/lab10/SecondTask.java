package com.example.lab10;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dynamicRequest")
public class SecondTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        try {

            Connection connection = DbConnection.GetConnection();

            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);

            PreparedStatement stm = connection.prepareStatement("Select * from users where id =? ");
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                writer.println(result.getInt(1) + " " + result.getString(2));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            writer.close();
        }
    }
}
