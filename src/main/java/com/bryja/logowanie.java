package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
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
                    response.sendRedirect(request.getContextPath() + "/home");
                    return;
                }
            }
        }

        File log = new File("log.txt");
        try{
            if(log.exists()==false){
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.append(getClientIpAddress(request)+"\n");
            out.close();
        }catch(IOException e){
            System.out.println("COULD NOT LOG!!");
        }


        request.setAttribute("error", "invalid login");
        response.sendRedirect(request.getContextPath() + "/home?error=login");
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR" };

    private String getClientIpAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }

        return request.getRemoteAddr();
    }
}
