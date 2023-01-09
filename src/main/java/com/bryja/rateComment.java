package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "rateComment", value = "/rateComment")
public class rateComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ocena = request.getParameter("rating");
        String id = request.getParameter("postid");
        String id2 = request.getParameter("odpowiedzid");
        int postid = Integer.parseInt(id);
        int odpowiedzid = Integer.parseInt(id2);
        int ocenka = Integer.parseInt(ocena);
        System.out.println(postid+odpowiedzid+ocenka);

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


        posty.get(postid).getOdps().get(odpowiedzid).suma_ocen+=ocenka;
        int suma = posty.get(postid).getOdps().get(odpowiedzid).suma_ocen;
        posty.get(postid).getOdps().get(odpowiedzid).ilosc_ocen+=1;
        int ilosc =posty.get(postid).getOdps().get(odpowiedzid).ilosc_ocen;
        posty.get(postid).getOdps().get(odpowiedzid).ocena=(int)(suma/ilosc);


        FileOutputStream f = new FileOutputStream(new File("posts.ser"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(posty);
        o.close();
        f.close();
        // HttpSession session = request.getSession();
        // User logged = (User)session.getAttribute("user");
        // logged.getPosty().add()
        response.sendRedirect(request.getContextPath() + "/home");



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
