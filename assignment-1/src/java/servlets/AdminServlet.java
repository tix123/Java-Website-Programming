package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminServlet extends HttpServlet {

    private final String ADMIN = "admin";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = getServletContext().getRealPath("/WEB-INF/homeitems.txt");
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("login");
        } else {
            if (!username.equals(ADMIN)) {
                response.sendRedirect("inventory");
            } else {

                int totalValue = 0;
                int mostExpensivePrice = 0;
                String mostExpensiveItem = "";
                String ownerOfmostExpensiveItem = "";

                String content = br.readLine();

                while (content != null) {
                    String[] contentArray = content.split(",");
                    int price = Integer.parseInt(contentArray[3]);
                    String item = contentArray[2];
                    String owner = contentArray[0];

                    totalValue += price;
                    if (price > mostExpensivePrice) {
                        mostExpensivePrice = price;
                        mostExpensiveItem = item;
                        ownerOfmostExpensiveItem = owner;
                    }
                    content = br.readLine();
                }

                br.close();

                request.setAttribute("total", totalValue);
                request.setAttribute("owner", ownerOfmostExpensiveItem);
                request.setAttribute("item", mostExpensiveItem);
                request.setAttribute("price", mostExpensivePrice);
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                        .forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
