package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "lista5zad1part2", value = "/lista5zad1part2")
public class lista5zad1part2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String dane = request.getParameter("dane");
        String logininnit = getServletContext().getInitParameter("Login");
        String passwordinnit = getServletContext().getInitParameter("Password");

        out.println("<h3>Hej!</h3>");
        if(login.equals(logininnit)&&password.equals(passwordinnit)){
            out.println("<h3>Konto znaleziono, hej "+login+"!</h3>");
        }
        else{
            out.println("<h3>Konto nie istnieje lub wpisano bledne haslo!</h3>");
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
