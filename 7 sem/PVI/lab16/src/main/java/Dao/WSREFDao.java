package Dao;

import Entities.WSREF;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WSREFDao {
    private static Connection connection;

    public WSREFDao() {
        try {
            connection = DbConnection.GetConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<WSREF> getWSREF(String filter) throws SQLException {
        String[] searchWords;
        String sql = "SELECT * FROM WSREF WHERE ";
        if (filter == null) {
            sql += "description LIKE '%%'";
        } else {
            searchWords = filter.split(" ");
            for (int i = 0; i < searchWords.length; i++) {
                if (i != 0)
                    sql += " OR ";
                sql += "(description LIKE ?)";
            }
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (filter != null) {
                searchWords = filter.split(" ");
                for (int i = 0; i < searchWords.length; i++) {
                    preparedStatement.setString(i + 1, "%" + searchWords[i] + "%");
                }
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<WSREF> wsref = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String url = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    int minus = resultSet.getInt(4);
                    int plus = resultSet.getInt(5);
                    wsref.add(new WSREF(id, url, description, minus, plus));
                }
                return wsref;
            }
        }
    }

    public void addWSREF(WSREF WSREF) throws SQLException {
        try (CallableStatement callableStatement = connection.prepareCall("EXEC AddWSREF ?,?,?,?")) {
            callableStatement.setString(1, WSREF.getUrl());
            callableStatement.setString(2, WSREF.getDescription());
            callableStatement.setInt(3, WSREF.getMinus());
            callableStatement.setInt(4, WSREF.getPlus());
            callableStatement.execute();
        }
    }

    public void updateWSREF(WSREF wsref) throws SQLException {
        if (wsref.getUrl() == null || wsref.getDescription() == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE WSREF SET plus = ?, minus = ? WHERE id = ?"
            )) {
                preparedStatement.setInt(1, wsref.getPlus());
                preparedStatement.setInt(2, wsref.getMinus());
                preparedStatement.setInt(3, wsref.getId());
                preparedStatement.executeUpdate();
            }
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE WSREF SET url = ?, description = ?, plus = ?, minus = ? WHERE id = ?"
            )) {
                preparedStatement.setString(1, wsref.getUrl());
                preparedStatement.setString(2, wsref.getDescription());
                preparedStatement.setInt(3, wsref.getPlus());
                preparedStatement.setInt(4, wsref.getMinus());
                preparedStatement.setInt(5, wsref.getId());
                preparedStatement.executeUpdate();
            }
        }
    }

    public void deleteWSREF(int id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM WSREFComment WHERE wsref_id = " + id);
            statement.executeUpdate("DELETE FROM WSREF WHERE id = " + id);
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}