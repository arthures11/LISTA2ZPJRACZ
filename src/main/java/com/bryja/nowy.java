package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "nowy", value = "/nowy")
public class nowy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imie = request.getParameter("imie");
        String pesel = request.getParameter("pesel");
        String dataUrodzenia = request.getParameter("data_urodzenia");
        String plec = request.getParameter("plec");
        String zawod = request.getParameter("zawod");
        String email = request.getParameter("email");
        String wzrost = request.getParameter("wzrost");
        String hobby = request.getParameter("hobby");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if(validator(imie,pesel,dataUrodzenia,plec,zawod,email,wzrost,hobby)){
            out.println("<h3>imie: "+imie+"<br>pesel: "+pesel+"<br>data ur: "+dataUrodzenia+"<br>plec: "+plec+"<br>zawod: "+zawod+"<br>email: "+email+"<br>wzrost: "+wzrost+"<br>hobby: "+hobby);


            out.println("</h3>");
        }
        else{
            out.println("<h3>Blednie wpisane, zoastaniesz przekierowany za 5sekund</h3>");
            try {
                Thread.sleep(5000);
                response.sendRedirect(request.getContextPath() + "/tabela");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public static boolean validator(String imie,String pesel,String dataUrodzenia,String plec,String zawod,String email,String wzrost,String hobby){
        if(!isFullname(imie)){System.out.println(imie);return false;}
        if(!isPESEL(pesel)){System.out.println(pesel);return false;}
        if(!isDate(dataUrodzenia)){System.out.println(dataUrodzenia);return false;}
        if(!isPlec(plec)){System.out.println(plec);return false;}
        if(!isZawod(zawod)){System.out.println(zawod);return false;}
        if(!isMail(email)){System.out.println(email);return false;}
        if(!isWzrost(wzrost)){System.out.println(wzrost);return false;}
        if(!isHobby(hobby)){System.out.println(hobby);return false;}
        return true;
    }
    public static boolean isFullname(String str) {
        String expression = "^[a-zA-Z\\s]+";
        return str.matches(expression);
    }
    public static boolean isPESEL(String str) {
        String expression = "^[0-9]{2}([02468]1|[13579][012])(0[1-9]|1[0-9]|2[0-9]|3[01])[0-9]{5}$";
        return str.matches(expression);
    }
    //^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$
    public static boolean isDate(String str) { //yyyy-mm-dd
        String expression = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
        return str.matches(expression);
    }
    public static boolean isPlec(String str) {
        String expression = "^(Mezczyzna|Kobieta)$";
        return str.matches(expression);
    }
    public static boolean isZawod(String str) {
        String expression = "^Piekarz$";
        return str.matches(expression);
    }
    //^((\w[^\W]+)[\.\-]?){1,}\@(([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3})|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$
    public static boolean isMail(String str) {
        String expression = "^((\\w[^\\W]+)[\\.\\-]?){1,}\\@(([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        return str.matches(expression);
    }
    public static boolean isWzrost(String str) {
        String expression = "^\\d{3}$";
        return str.matches(expression);
    }
    public static boolean isHobby(String str) {
        String expression = "^(Gry\\skomputerowe|Sport)$";
        return str.matches(expression);
    }
}
