package servlets;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String action = request.getParameter("action");

        if (username == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        } else {
            if (action == null || action.isEmpty()) {
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                        .forward(request, response);
            } else if (action.equals("logout")) {
                session.invalidate();
                session = request.getSession();

                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                        .forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String username = request.getParameter("username");

        String item = request.getParameter("item");

        String action = request.getParameter("action");

        ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("itemList");

        if (itemList == null) {
            itemList = new ArrayList<String>();
        }

        if (action.equals("register")) {
            session.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        }

        if (action.equals("add")) {
            itemList.add(item);
            session.setAttribute("itemList", itemList);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        }

        if (action.equals("delete")) {

            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).equals(item)) {
                    itemList.remove(i);
                }
            }

            session.setAttribute("itemList", itemList);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        }
    }

}
