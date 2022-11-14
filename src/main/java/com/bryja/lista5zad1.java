package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "lista5zad1", value = "/lista5zad1")
public class lista5zad1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Hej!</h3>");

        out.println("<form action='lista5zad1part2'>");
        out.println("<label style='display: inline-block;width: 95px' for=\"login\">login:</label>");
        out.println("<input type=\"text\" id=\"login\" name=\"login\"><br>");
        out.println("<label style='display: inline-block;width: 95px' for=\"password\">password:</label>");
        out.println("<input type=\"password\" id=\"password\" name=\"password\"><br>");
        out.println("<label style='display: inline-block;width: 95px' for=\"dane\">dane:</label>");
        out.println("<input type=\"text\" id=\"dane\" name=\"dane\"><br>");
        out.println("<input type=\"submit\"/>");
        out.println("</form>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
