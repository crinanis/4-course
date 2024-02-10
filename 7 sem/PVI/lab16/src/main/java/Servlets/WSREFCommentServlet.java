package Servlets;
import Entities.WSREFComment;
import Dao.WSREFCommentDao;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import com.google.gson.JsonSyntaxException;

@WebServlet("/WSREFComments")
public class WSREFCommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE_JSON = "application/json";
    private WSREFCommentDao wsrefCommentDao;

    @Override
    public void init() {
        wsrefCommentDao = new WSREFCommentDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int wsrefId = Integer.parseInt(request.getParameter("wsref_id"));
            List<WSREFComment> comments = wsrefCommentDao.getWSREFComments(wsrefId);
            sendJsonResponse(response, comments);
        } catch (SQLException | NumberFormatException e) {
            handleException(response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String json = request.getReader().readLine();
            WSREFComment comment = new Gson().fromJson(json, WSREFComment.class);
            comment.setStamp(new Date(System.currentTimeMillis()));
            wsrefCommentDao.addWSREFComment(comment);
            sendSuccessResponse(response);
        } catch (SQLException | JsonSyntaxException e) {
            handleException(response, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String json = request.getReader().readLine();
            WSREFComment comment = new Gson().fromJson(json, WSREFComment.class);
            if (comment.getId() == 0) {
                sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Comment ID is required for updating.");
                return;
            }
            wsrefCommentDao.updateWSREFComment(comment);
            sendSuccessResponse(response);
        } catch (SQLException | JsonSyntaxException e) {
            handleException(response, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int commentId = Integer.parseInt(request.getParameter("id"));
            wsrefCommentDao.deleteWSREFComment(commentId);
            sendSuccessResponse(response);
        } catch (SQLException | NumberFormatException e) {
            handleException(response, e);
        }
    }

    private void sendJsonResponse(HttpServletResponse response, Object data) throws IOException {
        response.setContentType(CONTENT_TYPE_JSON);
        response.setCharacterEncoding("UTF-8");
        String jsonResponse = new Gson().toJson(data);
        response.getWriter().write(jsonResponse);
    }


    private void sendSuccessResponse(HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE_JSON);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\": true}");
    }

    private void sendErrorResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        response.setContentType(CONTENT_TYPE_JSON);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\": false, \"error\": \"" + message + "\"}");
    }

    private void handleException(HttpServletResponse response, Exception e) throws IOException {
        e.printStackTrace();
        sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
    }
}