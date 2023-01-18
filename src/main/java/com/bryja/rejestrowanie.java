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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "rejestrowanie", value = "/rejestrowanie")
public class rejestrowanie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("name");
        String pass = request.getParameter("password");
        List<User> userzy = new ArrayList<>();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        QueryHelpers helpers = new QueryHelpers();
        userzy = helpers.getUserList();


        for(int i=0;i<userzy.size();i++){
            if(userzy.get(i).getUsername().equals(key)){
                System.out.println("ten uzytkownik juz istnieje");
                RequestDispatcher dispatcher = request.getSession().getServletContext()
                        .getRequestDispatcher("/home");
                dispatcher.forward(request, response);
                return;
            }
        }


        try {
            Class.forName ("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            Statement statement = connection.createStatement();
            //"insert into users (id, password, username) values (default, 'testy', 'testy')";
            String insertNewUser = "insert into users (id, password, username) values (default, ?, ?)";
            PreparedStatement statement2 = connection.prepareStatement(insertNewUser);
            statement2.setString(1, pass);
            statement2.setString(2, key);
            statement2.executeUpdate();
            System.out.println("dodano usera nowego: "+key+"   "+pass);
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
