package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "logowanie", value = "/logowanie")
public class logowanie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("namelog");
        String pass = request.getParameter("passwordlog");

        List<User> userzy = new ArrayList<>();
        FileInputStream fi = new FileInputStream(new File("myObjects.ser"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        try {
            userzy = (List<User>) oi.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Counter a = new Counter();
        oi.close();
        fi.close();
        for(int i=0;i<userzy.size();i++){
            if(userzy.get(i).getUsername().equals(key)){
                if(userzy.get(i).getPassword().equals(pass)){
                    //System.out.println("pomyslnie zalogowalem");
                    Counter.addSession();
                    HttpSession session = request.getSession();
                    session.setAttribute("user", userzy.get(i));
                   // response.sendRedirect(request.getParameter("origin"));
                    response.sendRedirect(request.getContextPath() + "/home.jsp");
                    return;
                }
            }
        }
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
            System.out.println("NIEUDANA PROBA LOGOWANIA: "+ipAddress);
        }
        request.setAttribute("error", "invalid login");
        response.sendRedirect(request.getContextPath() + "/home.jsp?error=login");
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
