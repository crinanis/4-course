package Dao;
import Entities.WSREFComment;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WSREFCommentDao {
    private static Connection connection;

    public WSREFCommentDao() {
        try {
            connection = DbConnection.GetConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<WSREFComment> getWSREFComments(int wsref_id) throws SQLException {
        List<WSREFComment> WSREFComments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM WSREFComment WHERE wsref_id = ?"
        )) {
            preparedStatement.setInt(1, wsref_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    wsref_id = resultSet.getInt(2);
                    String session_id = resultSet.getString(3);
                    Date stamp = resultSet.getDate(4);
                    String comment = resultSet.getString(5);
                    String usr = resultSet.getString(6);
                    WSREFComments.add(new WSREFComment(id, wsref_id, session_id, stamp, comment, usr));
                    System.out.println(id + wsref_id + session_id + stamp + comment + usr);
                }
            }
        }
        return WSREFComments;
    }

    public void addWSREFComment(WSREFComment WSREFComment) throws SQLException {
        System.out.println(WSREFComment.getWsref_id());
        try (CallableStatement callableStatement = connection.prepareCall("EXEC AddWSREFComment ?,?,?,?,?")) {
            callableStatement.setInt(1, WSREFComment.getWsref_id());
            callableStatement.setString(2, WSREFComment.getSession_id());
            callableStatement.setDate(3, WSREFComment.getStamp());
            callableStatement.setString(4, WSREFComment.getComment());
            callableStatement.setString(5, WSREFComment.getUsr());
            callableStatement.execute();
        }
    }

    public void updateWSREFComment(WSREFComment WSREFComment) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE WSREFComment SET comment = ? WHERE id = ?"
        )) {
            preparedStatement.setString(1, WSREFComment.getComment());
            preparedStatement.setInt(2, WSREFComment.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteWSREFComment(int id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM WSREFComment WHERE id = " + id);
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
