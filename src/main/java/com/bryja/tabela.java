package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "tabela", value = "/tabela")
public class tabela extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Hello</h3>");
        int pobrane = Integer.parseInt(request.getParameter("rozmiar"));
        out.println("<h3>rozmiar to :"+ pobrane+"</h3>");
        out.println("<table style='width:200px'; 'table-layout:fixed'; 'padding-bottom:300px'>");
        for(int i=0;i<=pobrane;i++){
            out.println("<tr>");
            for(int j=0;j<=pobrane;j++){
                if(i==0&&j==0){
                    out.println("<td style='border: 1px solid #dddddd'; 'text-align:center';'padding: 8px;'>X</td>");
                }
                else if(j==0){
                    out.println("<td style='border: 1px solid #dddddd'; 'text-align:center';'padding: 8px;'>"+i+"</td>");
                }
                else if(i==0){
                    out.println("<td style='border: 1px solid #dddddd'; 'text-align:center';'padding: 8px;'>"+j+"</td>");
                }
                else{
                    out.println("<td style='border: 1px solid #dddddd'; 'text-align:center';'padding: 8px;'>"+j*i+"</td>");
                }
            }
            out.println("</tr>");
        }
        out.println("</table><br><br>");
        out.println("<form action='nowy'>");
        out.println("<label style='display: inline-block;width: 95px' for=\"imie\">imie:</label>");
        out.println("<input type=\"text\" id=\"imie\" name=\"imie\"><br>");
        out.println("<label style='display: inline-block;width: 95px' for=\"pesel\">PESEL:</label>");
        out.println("<input type=\"text\" id=\"pesel\" name=\"pesel\"><br>");
        out.println("<label style='display: inline-block;width: 95px' for=\"data_urodzenia\">data ur:</label>");
        out.println("<input type=\"text\" id=\"data_urodzenia\" name=\"data_urodzenia\"><br>");
        out.println("<label style='display: inline-block;width: 95px' for=\"plec\">plec:</label>");
        out.println("<input type=\"text\" id=\"plec\" name=\"plec\"><br>");
        out.println("<label style='display: inline-block;width: 95px' for=\"zawod\">zawod:</label>");
        out.println("<input type=\"text\" id=\"zawod\" name=\"zawod\"><br>");
        out.println("<label style='display: inline-block;width: 95px' for=\"email\">email:</label>");
        out.println("<input type=\"text\" id=\"email\" name=\"email\"><br>");
        out.println("<label style='display: inline-block;width: 95px' for=\"wzrost\">wzrost:</label>");
        out.println("<input type=\"text\" id=\"wzrost\" name=\"wzrost\"><br>");
        out.println("<label style='display: inline-block;width: 95px' for=\"hobby\">hobby:</label>");
        out.println("<input type=\"text\" id=\"hobby\" name=\"hobby\"><br>");
        out.println("<input type=\"submit\"/>");
        out.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
