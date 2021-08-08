package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArithmeticCalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("message", "---");
        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String first = request.getParameter("first");
        String second = request.getParameter("second");

        request.setAttribute("first", first);
        request.setAttribute("second", second);

        if (first == null || second == null
                || first.equals("") || second.equals("")
                || !first.matches("[0-9]*") || !second.matches("[0-9]*")) {
            request.setAttribute("message", "invalid");
            getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp")
                    .forward(request, response);
            return;
        }

        int firstNum = Integer.parseInt(first);
        int secondNum = Integer.parseInt(second);
        String submit = request.getParameter("submit");

        if (submit.equals("+")) {
            int result = firstNum + secondNum;
            request.setAttribute("message", result);
        }

        if (submit.equals("-")) {
            int result = firstNum - secondNum;
            request.setAttribute("message", result);
        }

        if (submit.equals("*")) {
            int result = firstNum * secondNum;
            request.setAttribute("message", result);
        }

        if (submit.equals("%")) {
            int result = firstNum % secondNum;
            request.setAttribute("message", result);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp")
                .forward(request, response);
    }
}
