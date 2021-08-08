package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Category;
import services.CategoryService;

public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoryService cs = new CategoryService();

        try {
            List<Category> categories = cs.getAll();
            request.setAttribute("categories", categories);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) {
            try {
                String categoryId = request.getParameter("categoryId");

                Category category = cs.get(Integer.parseInt(categoryId));
                request.setAttribute("selectedCategory", category);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "error");
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/category.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoryService cs = new CategoryService();

        String action = request.getParameter("action");
        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");

        try {
            switch (action) {
                case "create":
                    cs.insert(categoryName);
                    break;

                case "update":
                    cs.update(Integer.parseInt(categoryId), categoryName);
                    break;

            }
            request.setAttribute("message", action);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
            List<Category> categories = cs.getAll();
            request.setAttribute("categories", categories);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/category.jsp")
                .forward(request, response);
    }

}
