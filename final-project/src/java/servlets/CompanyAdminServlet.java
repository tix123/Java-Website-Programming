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
import models.Item;
import models.Role;
import services.AccountService;
import models.User;
import services.InventoryService;

public class CompanyAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        AccountService as = new AccountService();
        InventoryService is = new InventoryService();
        int companyId = 0;

        try {
            User user = as.get(email);
            companyId = user.getCompany();
            request.setAttribute("companyId", companyId);

            List<User> users = as.getAllByCompany(companyId);
            request.setAttribute("users", users);

            List<Role> roles = as.getAllRoles();
            request.setAttribute("roles", roles);

            List<Company> companys = as.getAllCompanys();
            request.setAttribute("companys", companys);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        String action = request.getParameter("action");
        if (action != null) {
            try {
                switch (action) {
                    case "edit":
                        String userEmail = request.getParameter("user_email");

                        List<Role> companyRoles = as.getAllRolesByCompanyAdmin();
                        request.setAttribute("companyRoles", companyRoles);

                        User user = as.getByCompanyAdmin(userEmail, companyId);
                        request.setAttribute("selectedUser", user);
                        break;

                    case "search":
                        String keyword = request.getParameter("keyword");
                        List<Item> items = is.searchByCompanyAdmin(keyword, companyId);
                        request.setAttribute("items", items);
                        break;
                }

            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "error");
            }
        }

        /*
        if (action != null && action.equals("edit")) {
            try {
                String userEmail = request.getParameter("user_email");

                List<Role> companyRoles = as.getAllRolesByCompanyAdmin();
                request.setAttribute("companyRoles", companyRoles);

                User user = as.getByCompanyAdmin(userEmail, companyId);
                request.setAttribute("selectedUser", user);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "error");
            }
        }
         */
        getServletContext().getRequestDispatcher("/WEB-INF/companyAdmin.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        AccountService as = new AccountService();

        String action = request.getParameter("action");
        String userEmail = request.getParameter("user_email");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String active = request.getParameter("active");

        try {
            User user = as.get(email);
            int companyId = user.getCompany();

            switch (action) {
                case "create":
                    as.insert(userEmail, firstName, lastName, password, companyId);
                    request.setAttribute("message", action);
                    break;

                case "update":
                    as.updateByCompanyAdmin(userEmail, Boolean.valueOf(active), firstName, lastName, password, Integer.parseInt(role), companyId);
                    request.setAttribute("message", action);
                    break;

                case "delete":
                    if (email.equals(userEmail)) {
                        request.setAttribute("message", "invalid");
                    } else {
                        as.deleteByCompanyAdmin(userEmail, companyId);
                        request.setAttribute("message", action);
                    }
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
            User user = as.get(email);
            int companyId = user.getCompany();
            request.setAttribute("companyId", companyId);

            List<User> users = as.getAllByCompany(companyId);
            request.setAttribute("users", users);

            List<Role> roles = as.getAllRoles();
            request.setAttribute("roles", roles);

            List<Company> companys = as.getAllCompanys();
            request.setAttribute("companys", companys);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/companyAdmin.jsp")
                .forward(request, response);
    }
}
