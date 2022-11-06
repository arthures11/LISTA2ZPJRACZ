package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "lista4zad1", value = "/lista4zad1")
public class lista4zad1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Helloasdasd</h3>");

        out.println("<form action='lista4zad1part2'>");
        out.println("<label style='display: inline-block;width: 95px' for=\"numer\">numer:</label>");
        out.println("<input type=\"text\" id=\"numer\" name=\"numer\"><br>");
        out.println("<input type=\"submit\"/>");
        out.println("</form>");
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
