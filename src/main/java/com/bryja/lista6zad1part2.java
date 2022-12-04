package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Random;

@WebServlet(name = "lista6zad1part2", value = "/lista6zad1part2")
public class lista6zad1part2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("strona");
        //File file = new File("/WEB-INF/czytanie.txt");
        FileWriter fstream = new FileWriter("C:\\Users\\Student\\Downloads\\LISTA2ZPJRACZ-main\\target\\LISTA2ZPJRACZ\\WEB-INF\\czytanie.txt",true);
        BufferedWriter out = new BufferedWriter(fstream);
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String id = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        out.write("\n"+id+";"+password);
        out.close();
        PrintWriter pw = response.getWriter();
        pw.println("Twoja podstrona bedzie na: ?skrot="+id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
