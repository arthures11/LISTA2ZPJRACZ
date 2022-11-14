package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "lista5zad2", value = "/lista5zad2")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class lista5zad2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Hej!</h3>");

        out.println("<form method='post' action='lista5zad2part2' enctype='multipart/form-data'>");
        out.println("<label for=\"foldery\">Wybierz do ktorego folderu:</label>");
        out.println("<select name=\"foldery\" id=\"foldery\">");
        out.println("<option value=\"volvo\">Volvo</option>");
        out.println("<option value=\"saab\">Saab</option>");
        out.println("<option value=\"opel\">Opel</option>");
        out.println("<option value=\"audi\">Audi</option>");
        out.println("<option value=\"hyundai\">Hyundai</option>");
        out.println("</select>");
        out.println("<label for=\"myfile\">Wybierz plik:</label>");
        out.println("<input type=\"file\" id=\"myfile\" name=\"myfile\"><br>");
        out.println("<input type=\"submit\"/>");
        out.println("</form>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
