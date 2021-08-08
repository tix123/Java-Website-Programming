package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (request.getQueryString() != null && request.getQueryString().equals("logout")) {
            session.invalidate();
            request.setAttribute("message", "You have successfully logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
            return;
        }

        int roleId = 0;
        Object roleIdObject = session.getAttribute("roleId");
        String email = (String) session.getAttribute("email");

        if (email != null && roleIdObject != null) {
            roleId = (int) roleIdObject;
            if (roleId == 1 || roleId == 3) {
                response.sendRedirect("admin");
            } else {
                response.sendRedirect("inventory");
            }
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AccountService as = new AccountService();
        User user = as.login(email, password);

        if (user == null) {
            request.setAttribute("message", "Invalid login.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("email", email);

        String fristName = user.getFirstName();
        session.setAttribute("firstName", fristName);

        String lastName = user.getLastName();
        session.setAttribute("lastName", lastName);

        int roleId = user.getRole();
        session.setAttribute("roleId", roleId);

        if (roleId == 1 || roleId == 3) {
            response.sendRedirect("admin");
        } else {
            response.sendRedirect("inventory");
        }
    }

}
