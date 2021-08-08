package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CompanyAdminFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        //before
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();

        Object roleIdObject = session.getAttribute("roleId");
        int roleId = 0;
        if (roleIdObject == null) {
            httpResponse.sendRedirect("login");
            return;
        } else {
            roleId = (int) roleIdObject;
            if (roleId == 2) {
                httpResponse.sendRedirect("inventory");
                return;
            }
        }

        chain.doFilter(request, response);

        //after
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //nothing here
    }

    @Override
    public void destroy() {
        //nothing here
    }

}
