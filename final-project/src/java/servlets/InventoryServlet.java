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
import models.Category;
import models.Item;
import services.CategoryService;
import services.InventoryService;

public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        InventoryService is = new InventoryService();
        CategoryService cs = new CategoryService();

        try {
            List<Item> items = is.getAllItems(email);
            request.setAttribute("items", items);
            List<Category> categories = cs.getAll();
            request.setAttribute("categories", categories);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) {
            try {
                String itemId = request.getParameter("itemId");

                Item item = is.get(Integer.parseInt(itemId), email);
                request.setAttribute("selectedItem", item);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "error");
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        InventoryService is = new InventoryService();
        CategoryService cs = new CategoryService();

        String action = request.getParameter("action");
        String itemId = request.getParameter("itemId");
        String itemName = request.getParameter("itemName");
        String price = request.getParameter("price");
        String category = request.getParameter("category");

        try {
            switch (action) {
                case "create":
                    is.insert(Integer.parseInt(category), itemName, Double.parseDouble(price), email);
                    break;

                case "update":
                    is.update(Integer.parseInt(itemId), Integer.parseInt(category), itemName, Double.parseDouble(price), email);
                    break;

                case "delete":
                    is.delete(Integer.parseInt(itemId), email);
                    break;
            }
            request.setAttribute("message", action);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
            List<Item> items = is.getAllItems(email);
            request.setAttribute("items", items);
            List<Category> categories = cs.getAll();
            request.setAttribute("categories", categories);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request, response);
    }

}
