package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "lista6zad1", value = "/lista6zad1")
public class lista6zad1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String filename = "/WEB-INF/czytanie.txt";
        String paramName = "skrot";
        String paramValue = request.getParameter("skrot");
        ServletContext context = getServletContext();
        PrintWriter pw = response.getWriter();
        if(paramValue==null) {
            pw.println("<h3>Hej!</h3>");

            pw.println("<form action='lista6zad1part2'>");
            pw.println("<label style='display: inline-block;width: 95px' for=\"strona\">strona:</label>");
            pw.println("<input type=\"text\" id=\"strona\" name=\"strona\"><br>");
            pw.println("<input type=\"submit\"/>");
            pw.println("</form>");
            pw.close();
        }


        InputStream inp = context.getResourceAsStream(filename);
        if (inp != null) {
            InputStreamReader isr = new InputStreamReader(inp);
            BufferedReader reader = new BufferedReader(isr);
            pw.println("<html><head><title>Read Text File</title></head><body bgcolor='cyan'></body></html>");
            String text = "";
                if(paramValue.equals("test")){
                response.sendRedirect("https://www.google.com/");
            }
            while ((text = reader.readLine()) != null) {
                String[] parts = text.split(";");
                String strona = parts[1];
                if(paramValue.equals(parts[0])){
                    response.sendRedirect(strona);

                }
                pw.println("<h2><i><b>"+text+"</b></i></b><br>");
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

}}
