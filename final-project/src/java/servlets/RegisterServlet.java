package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Company;
import models.User;
import services.AccountService;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AccountService as = new AccountService();

        try {
            List<Company> companys = as.getAllCompanys();
            request.setAttribute("companys", companys);
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String company = request.getParameter("company");

        AccountService as = new AccountService();

        try {
            List<User> users = as.getAll();

            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    request.setAttribute("message", "invalid");
                    getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                            .forward(request, response);
                    return;
                }
            }

            List<Company> companys = as.getAllCompanys();
            request.setAttribute("companys", companys);

            as.insert(email, firstName, lastName, password, Integer.parseInt(company));
            request.setAttribute("message", "create");

        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request, response);
    }

}
