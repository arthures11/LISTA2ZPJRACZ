package com.bryja;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet(name = "lista5zad2part2", value = "/lista5zad2part2")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class lista5zad2part2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {












       // Path copied = Paths.get("src/test/resources/copiedWithNio.txt");
       // Path file = Paths.get(request.getParameter("myfile"));
       // System.out.println(file);
        //Path folder = Paths.get(request.getParameter("foldery"));
        //System.out.println(folder);

       // Files.copy(file, folder, StandardCopyOption.REPLACE_EXISTING);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String folder = request.getParameter("foldery");
            Part filePart = request.getPart("myfile");
            String fileName = filePart.getSubmittedFileName();
            String currentPath = new java.io.File(".").getCanonicalPath();
            System.out.println(currentPath);
            for (Part part : request.getParts()) {
                part.write(currentPath + "\\" + folder + "\\" + fileName);
            }
            System.out.println("OK");
            response.getWriter().print("OK. Powiodlo sie.");
        }
        catch (Exception e){
            response.getWriter().print("BLAD, sprobuj ponownie.");
        }

    }
}
