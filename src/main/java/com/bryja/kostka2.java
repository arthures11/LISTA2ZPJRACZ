package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "kostka2", value = "/kostka2")
public class kostka2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int ktory = 0;
        Cookie nef[]=request.getCookies();
        String numer = request.getParameter("numer");
        if(nef==null){
            Cookie ck=new Cookie("punkty",getServletContext().getInitParameter("KostkaStartingValue"));//creating cookie object
            response.addCookie(ck);//adding cookie in the response
            ck.setPath("/");
            ck.setMaxAge(99*99*99);
            response.sendRedirect(request.getContextPath() + "/kostka2?numer="+numer);
            out.close();
        }
            for (int i = 0; i < nef.length; i++) {
                if (nef[i].getName().equals("punkty") && isKostka(numer)) {
                    ktory = i;
                    break;
                }
                if (i + 1 == nef.length) {
                    Cookie ck=new Cookie("punkty",getServletContext().getInitParameter("KostkaStartingValue"));//creating cookie object
                    response.addCookie(ck);//adding cookie in the response
                    ck.setPath("/");
                    ck.setMaxAge(99*99*99);
                }
            }
        int kostkawinpkt = Integer.parseInt(getServletContext().getInitParameter("KostkaWinValue"));
        int kostkalostpkt = Integer.parseInt(getServletContext().getInitParameter("KostkaLoseValue"));
        int Startingpoints = Integer.parseInt(getServletContext().getInitParameter("KostkaStartingValue"));
        if(isKostka(numer)){
            out.println("<h3>Twoj numer: "+numer+"</h3>");
            out.println("<h3>numer wylosowany: "+randomNum+"</h3>");
            if(Integer.parseInt(numer)==randomNum){
                nef[ktory].setValue(String.valueOf(Integer.parseInt(nef[ktory].getValue())+kostkawinpkt));
                out.println("<h3>Wygrales: +"+kostkawinpkt+"pkt</h3>");
            }
            else{
                nef[ktory].setValue(String.valueOf(Integer.parseInt(nef[ktory].getValue())-kostkalostpkt));
                out.println("<h3>Przegarales: -"+kostkalostpkt+"pkt</h3>");
            }
            if(Integer.parseInt(nef[ktory].getValue())<=0){
                System.out.println("ABC");
                out.println("<h3>Przegrales totalnie, punkty zresetowane do 50</h3>");
                nef[ktory].setValue(String.valueOf(Startingpoints));
            }
            out.println("<h3>Twoje punkty aktualnie: "+nef[ktory].getValue()+"</h3>");
            response.addCookie(nef[ktory]);
            nef[ktory].setPath("/");
            nef[ktory].setMaxAge(99*99*99);
        }
        else{
            response.sendRedirect(request.getContextPath() + "/kostka");
        }
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public static boolean isKostka(String strNum) {
        if(strNum.equals("1")||strNum.equals("2")||strNum.equals("3")||strNum.equals("4")||strNum.equals("5")||strNum.equals("6")){
            return true;
        }
        else return false;
    }
}
