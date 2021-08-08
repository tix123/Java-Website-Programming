package servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InventoryServlet extends HttpServlet {

    private final String ADMIN = "admin";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("login");
        } else {
            if (username.equals(ADMIN)) {
                response.sendRedirect("admin");
            } else {

                int totalValue = calcTotal(username);

                request.setAttribute("total", totalValue);

                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                        .forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String path = getServletContext().getRealPath("/WEB-INF/homeitems.txt");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));

        String category = request.getParameter("category");
        String item = request.getParameter("item");
        String priceString = request.getParameter("price");

        if (!category.equals("bedroom") && !category.equals("garage") && !category.equals("kitchen") && !category.equals("living room")) {
            int totalValue = calcTotal(username);
            request.setAttribute("total", totalValue);
            request.setAttribute("message", "Invalid. Please re-enter.");
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                    .forward(request, response);
        } else if (item == null || item.equals("")) {
            int totalValue = calcTotal(username);
            request.setAttribute("total", totalValue);
            request.setAttribute("message", "Invalid. Please re-enter.");
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                    .forward(request, response);
        } else if (priceString == null || priceString.equals("")) {
            int totalValue = calcTotal(username);
            request.setAttribute("total", totalValue);
            request.setAttribute("message", "Invalid. Please re-enter.");
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                    .forward(request, response);
        } else if (!priceString.matches("[0-9]*")) {
            int totalValue = calcTotal(username);
            request.setAttribute("total", totalValue);
            request.setAttribute("message", "Invalid. Please re-enter.");
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                    .forward(request, response);
        } else {
            int price = Integer.parseInt(priceString);

            if (price > 9999 || price < 1) {
                int totalValue = calcTotal(username);
                request.setAttribute("total", totalValue);
                request.setAttribute("message", "Invalid. Please re-enter.");
                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                        .forward(request, response);
            } else {
                pw.println(username + "," + category + "," + item + "," + price);
                pw.close();

                request.setAttribute("message", "The item was successfully added to your inventory.");

                int totalValue = calcTotal(username);

                request.setAttribute("total", totalValue);

                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                        .forward(request, response);
            }
        }
    }

    int calcTotal(String username) throws IOException {
        String path = getServletContext().getRealPath("/WEB-INF/homeitems.txt");
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));

        int totalValue = 0;

        String content = br.readLine();
        while (content != null) {
            String[] contentArray = content.split(",");
            int price = Integer.parseInt(contentArray[3]);
            String owner = contentArray[0];

            if (username.equals(owner)) {
                totalValue += price;
            }

            content = br.readLine();
        }

        br.close();

        return totalValue;
    }
}
