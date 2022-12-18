package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "rejestrowanie", value = "/rejestrowanie")
public class rejestrowanie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("name");
        String pass = request.getParameter("password");
        List<User> userzy = new ArrayList<>();
        FileInputStream fi = new FileInputStream(new File("myObjects.ser"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        try {
            userzy = (List<User>) oi.readObject();
       } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        oi.close();
        fi.close();


        for(int i=0;i<userzy.size();i++){
            if(userzy.get(i).getUsername().equals(key)){
                System.out.println("ten uzytkownik juz istnieje");
                RequestDispatcher dispatcher = request.getSession().getServletContext()
                        .getRequestDispatcher("/home");
                dispatcher.forward(request, response);
                return;
            }
        }
        userzy.add(new User(key,pass));

        FileOutputStream f = new FileOutputStream(new File("myObjects.ser"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(userzy);

        o.close();
        f.close();
        RequestDispatcher dispatcher = request.getSession().getServletContext()
                .getRequestDispatcher("/home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
