package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class LoginServlet extends HttpServlet {

    private final String ADMIN = "admin";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");

        if (username == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        } else {

            if (request.getQueryString() == null) {
                if (username.equals(ADMIN)) {
                    response.sendRedirect("admin");
                } else {
                    response.sendRedirect("inventory");
                }
            } else if (request.getQueryString().equals("logout")) {
                session.invalidate();
                session = request.getSession();
                request.setAttribute("message", "You have successfully logged out.");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                        .forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String path = getServletContext().getRealPath("/WEB-INF/users.txt");
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean loginCheck = false;

        String content = br.readLine();
        while (content != null) {
            String[] contentArray = content.split(",");

            boolean nameCheck;
            boolean passwordCheck;

            if (username.equals(contentArray[0])) {
                nameCheck = true;
            } else {
                nameCheck = false;
            }

            if (password.equals(contentArray[1])) {
                passwordCheck = true;
            } else {
                passwordCheck = false;
            }

            if (nameCheck == true && passwordCheck == true) {
                loginCheck = true;
            }
            content = br.readLine();
        }

        if (loginCheck == false) {
            request.setAttribute("message", "Invalid login.");
            request.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);

        } else if (username.equals(ADMIN)) {
            session.setAttribute("username", username);
            response.sendRedirect("admin");
        } else {
            session.setAttribute("username", username);
            response.sendRedirect("inventory");
        }
    }

}
