package com.bryja;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class UserCheckFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        request.setAttribute("origin", request.getRequestURI());
       // System.out.println(request.getSession().getAttribute("user"));
        if (request.getRequestURI().contains("dupa") && request.getSession().getAttribute("user") == null) {
            System.out.println("wywalam");
            RequestDispatcher dispatcher = request.getSession().getServletContext()
                    .getRequestDispatcher("/kostka");
            dispatcher.forward(req, res);
            return;
        }

        chain.doFilter(request, response);
    }
}
