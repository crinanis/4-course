package com.example.lab10;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/procedure")
public class ThirdTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {

            Connection connection = DbConnection.GetConnection();

            CallableStatement callableStatement = connection.prepareCall("EXEC AddUser ?, ?, ?");
            callableStatement.setString(1, request.getParameter("name"));
            callableStatement.setString(2, request.getParameter("password"));
            callableStatement.setString(3, request.getParameter("email"));

            int rowsAffected = callableStatement.executeUpdate();
            writer.println("Rows affected: " + rowsAffected);

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            writer.close();
        }
    }
}
