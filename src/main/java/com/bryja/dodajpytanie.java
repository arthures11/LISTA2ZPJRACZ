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

@WebServlet(name = "dodajpytanie", value = "/dodajpytanie")
public class dodajpytanie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String tresc = request.getParameter("tresc_pytania");
        String autor = request.getParameter("author_pytania");
        List<Post> posty = new ArrayList<>();
        List<Odpowiedzi> odpsy = new ArrayList<>();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        QueryHelpers helpers = new QueryHelpers();
        posty = helpers.getPostList();
        odpsy = helpers.getodpsList();

        List<Odpowiedzi> temp = new ArrayList<>();
        for(int i=0;i<posty.size();i++){
            temp.clear();
            for(int j=0;j< odpsy.size();j++){
                if(odpsy.get(j).PostID==posty.get(i).getId()){
                    System.out.println("znalazlem");
                    temp.add(odpsy.get(j));
                }
            }
            posty.get(i).setOdps(temp);

        }


        Date date = new Date();
        try {
            Class.forName ("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            Statement statement = connection.createStatement();
            String insertPost = "insert into post (postID, pytanie, post_author, post_data, liczba_odpowiedzi) values (default, ?, ?, ?, ?)";
            PreparedStatement statement2 = connection.prepareStatement(insertPost);
            statement2.setString(1, tresc);
            statement2.setString(2, autor);
            statement2.setString(3, date.toString());
            statement2.setInt(4, 0);
            System.out.println("dodano post");
            statement2.executeUpdate();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
       // HttpSession session = request.getSession();
       // User logged = (User)session.getAttribute("user");
       // logged.getPosty().add()
        response.sendRedirect(request.getContextPath() + "/home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
