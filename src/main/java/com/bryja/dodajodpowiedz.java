package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "dodajodpowiedz", value = "/dodajodpowiedz")
public class dodajodpowiedz extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String tresc = request.getParameter("tresc");
        String idpost = request.getParameter("postid");
        String autor = request.getParameter("author");
        int id = Integer.parseInt(idpost);
        List<Post> posty = new ArrayList<>();
        FileInputStream fi = new FileInputStream(new File("posts.ser"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        try {
            posty = (List<Post>) oi.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        oi.close();
        fi.close();
        posty.get(id).getOdps().add(new Odpowiedzi(tresc,autor,dtf.format(now)));
        posty.get(id).liczba_odpowiedzi++;

         FileOutputStream f = new FileOutputStream(new File("posts.ser"));
         ObjectOutputStream o = new ObjectOutputStream(f);
         o.writeObject(posty);

         o.close();
         f.close();
        response.sendRedirect(request.getContextPath() + "/home");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
