package Servlets;
import Entities.User;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Users")
public class UserServlet extends HttpServlet {

    private boolean currentAdmin = false;
    private boolean isCurrentLogIn = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        isCurrentLogIn = true;
        response.sendRedirect(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json;
        if (isCurrentLogIn) {
            json = new Gson().toJson("SignIn");
            isCurrentLogIn = false;
        } else {
            json = handleUserRequest(request);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private String handleUserRequest(HttpServletRequest request) throws IOException {
        String json;
        User user = new Gson().fromJson(request.getReader().readLine(), User.class);

        if (!currentAdmin) {
            if (isAdminCredentials(user)) {
                json = new Gson().toJson("admin");
                currentAdmin = true;
            } else if (isClientCredentials(user)) {
                json = new Gson().toJson("client");
            } else {
                json = new Gson().toJson("error");
            }
        } else {
            if (isResetCredentials(user)) {
                json = new Gson().toJson("client");
                currentAdmin = false;
            } else {
                json = new Gson().toJson("admin");
            }
        }

        return json;
    }

    private boolean isAdminCredentials(User user) {
        return user.getUsername().equals("admin") && user.getPassword().equals("password");
    }

    private boolean isClientCredentials(User user) {
        return user.getUsername().equals("client") && user.getPassword().equals("password");
    }

    private boolean isResetCredentials(User user) {
        return user.getUsername().equals("reset") && user.getPassword().equals("reset");
    }
}
