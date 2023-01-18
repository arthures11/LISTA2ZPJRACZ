package com.bryja;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        List<Odpowiedzi> odpsy = new ArrayList<>();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        QueryHelpers helpers = new QueryHelpers();
        posty = helpers.getPostList();
        odpsy = helpers.getodpsList();

        //List<Odpowiedzi> temp = new ArrayList<>();
        for(int i=0;i<posty.size();i++){
          //  temp.clear();
            for(int j=0;j< odpsy.size();j++){
                if(odpsy.get(j).PostID==posty.get(i).id){
                    System.out.println("znalazlem");
                   /// temp.add(odpsy.get(j));
                    posty.get(i).odps.add(odpsy.get(j));
                }
            }
           // posty.get(i).setOdps(temp);

        }
       // posty.get(id).getOdps().add(new Odpowiedzi(tresc,autor,dtf.format(now)));
       // posty.get(id).liczba_odpowiedzi++;

        Date date = new Date();
        try {
            Class.forName ("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            Statement statement = connection.createStatement();
            String inserOdp = "insert into odpowiedzi (id, odp_author, tresc,odp_data,ocena,ilosc_ocen,suma_ocen,postID) values (default, ?, ?, ?, 0, 0, 0, ?)";
            PreparedStatement statement2 = connection.prepareStatement(inserOdp);
            statement2.setString(1, autor);
            statement2.setString(2, tresc);
            statement2.setString(3, date.toString());
            statement2.setInt(4, Integer.parseInt(idpost));
            statement2.executeUpdate();

            String updatePost = "UPDATE post SET liczba_odpowiedzi = liczba_odpowiedzi + 1 WHERE postID = ?";
            PreparedStatement statement3 = connection.prepareStatement(updatePost);
            statement3.setInt(1, id);
            statement3.executeUpdate();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/home");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
