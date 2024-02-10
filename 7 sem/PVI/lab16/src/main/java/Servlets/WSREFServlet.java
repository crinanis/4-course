package Servlets;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import Entities.WSREF;
import Dao.WSREFDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/WSREF")
public class WSREFServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String COOKIE_NAME_JSESSIONID = "JSESSIONID";
    private WSREFDao wsrefDao;

    @Override
    public void init() {
        wsrefDao = new WSREFDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String sessionId = "";
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(COOKIE_NAME_JSESSIONID)) {
                    sessionId = cookie.getValue();
                }
            }

            String json = new Gson().toJson(wsrefDao.getWSREF(request.getParameter("filter")));
            response.setContentType(CONTENT_TYPE_JSON);
            response.setHeader("sessionId", sessionId);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException e) {
            handleException(response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String json = request.getReader().readLine();
            WSREF ref = new Gson().fromJson(json, WSREF.class);
            wsrefDao.addWSREF(ref);
            sendSuccessResponse(response);
        } catch (SQLException | JsonSyntaxException e) {
            handleException(response, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String json = request.getReader().readLine();
            WSREF ref = new Gson().fromJson(json, WSREF.class);
            wsrefDao.updateWSREF(ref);
            sendSuccessResponse(response);
        } catch (SQLException | JsonSyntaxException e) {
            handleException(response, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int wsref_id = Integer.parseInt(request.getParameter("id"));
            wsrefDao.deleteWSREF(wsref_id);
            sendSuccessResponse(response);
        } catch (SQLException | NumberFormatException e) {
            handleException(response, e);
        }
    }

    private void sendSuccessResponse(HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE_JSON);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\": true}");
    }

    private void handleException(HttpServletResponse response, Exception e) throws IOException {
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType(CONTENT_TYPE_JSON);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\": false, \"error\": \"" + e.getMessage() + "\"}");
    }
}
