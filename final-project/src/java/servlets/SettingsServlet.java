package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Company;
import models.Role;
import services.AccountService;
import models.User;

public class SettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        AccountService as = new AccountService();

        try {
            User user = as.get(email);
            request.setAttribute("user", user);
            List<Role> roles = as.getAllRoles();
            request.setAttribute("roles", roles);
            List<Company> companys = as.getAllCompanys();
            request.setAttribute("companys", companys);
        } catch (Exception ex) {
            Logger.getLogger(SettingsServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/settings.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        AccountService as = new AccountService();

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String password = request.getParameter("password");
        String company = request.getParameter("company");
        String active = request.getParameter("active");

        int role = 0;

        try {
            User user = as.get(email);
            role = user.getRole();
            as.update(email, Boolean.valueOf(active), firstName, lastName, password, role, Integer.parseInt(company));
            request.setAttribute("message", "update");

            user = as.get(email);
            request.setAttribute("user", user);
            List<Role> roles = as.getAllRoles();
            request.setAttribute("roles", roles);
            List<Company> companys = as.getAllCompanys();
            request.setAttribute("companys", companys);

            firstName = user.getFirstName();
            session.setAttribute("firstName", firstName);

            lastName = user.getLastName();
            session.setAttribute("lastName", lastName);

        } catch (Exception ex) {
            Logger.getLogger(SettingsServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/settings.jsp")
                .forward(request, response);
    }

}
