package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "lista4zad1part2", value = "/lista4zad1part2")
public class lista4zad1part2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ktory = 0;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie nef[]=request.getCookies();
        String numer = request.getParameter("numer");
        if(nef==null){
            Cookie ck=new Cookie("na",numer);//creating cookie object
            response.addCookie(ck);//adding cookie in the response
            ck.setPath("/");
            ck.setMaxAge(99*99*99);
            out.println("<h3>Numer wynosi teraz: "+numer+"</h3>");
            out.close();
            return;
        }
        for(int i=0;i<nef.length;i++){
            if(nef[i].getName().equals("na")){
                ktory=i;
                break;
            }
            if(i+1==nef.length){
                Cookie ck=new Cookie("na",numer);//creating cookie object
                response.addCookie(ck);//adding cookie in the response
                ck.setPath("/");
                ck.setMaxAge(99*99*99);
            }
        }
        if(isNumeric(numer)){

            System.out.println(numer);
            System.out.println(nef[ktory].getValue());
            nef[ktory].setValue(String.valueOf(Integer.parseInt(nef[ktory].getValue())*Integer.parseInt(numer)));
            response.addCookie(nef[ktory]);
            nef[ktory].setPath("/");
            nef[ktory].setMaxAge(99*99*99);
            out.println("<h3>Numer wynosi teraz: "+nef[ktory].getValue()+"</h3>");
        }
        else{
            response.sendRedirect(request.getContextPath() + "/lista4zad1");
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
