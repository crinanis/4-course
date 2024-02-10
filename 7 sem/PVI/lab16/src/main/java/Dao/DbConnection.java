package Dao;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection = null;
    private static ServletContext context;

    public static Connection GetConnection() throws SQLException {
        if (connection != null) {
            return connection;
        }

        System.out.println("Trying to connect...");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("SQL Server JDBC Driver not found.");
            e.printStackTrace();
            throw new SQLException("SQL Server JDBC Driver not found.", e);
        }

        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=wsr;trustServerCertificate=true";
        String user = "sa";
        String pass = "12345";

        connection = DriverManager.getConnection(dbURL, user, pass);

        if (connection != null) {
            System.out.println("Connected...");
        }

        return connection;
    }
}
