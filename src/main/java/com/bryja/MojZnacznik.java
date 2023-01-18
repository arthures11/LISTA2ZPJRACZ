package com.bryja;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class MojZnacznik extends SimpleTagSupport {
    private int postid;
    private int odpowiedzid;

    public void setPostid(int id) {
        this.postid = id;
    }
    public void setOdpowiedzid(int id) {
        this.odpowiedzid = id;
    }

    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
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

        try {
            int ocenka = posty.get(postid).getOdps().get(odpowiedzid).ocena;
            out.println(" <form action=\"/rateComment\">\n" +
                    "   <label for=\"rating\">Oceń komentarz:</label><br>\n" +
                    "   <select id=\"rating\" name=\"rating\">\n" +
                    "   <option hidden=\"\" disabled=\"disabled\" selected=\"selected\" value=\"\">"+ocenka+"</option>\n" +
                    "       <option value=\"0\">0</option>\n" +
                    "       <option value=\"1\">1</option>\n" +
                    "       <option value=\"2\">2</option>\n" +
                    "       <option value=\"3\">3</option>\n" +
                    "       <option value=\"4\">4</option>\n" +
                    "       <option value=\"5\">5</option>\n" +
                    "       <option value=\"6\">6</option>\n" +
                    "       <option value=\"7\">7</option>\n" +
                    "       <option value=\"8\">8</option>\n" +
                    "       <option value=\"9\">9</option>\n" +
                    "       <option value=\"10\">10</option>\n" +
                    "   </select>\n" +
                    "   <input type=\"hidden\" id=\"postid\" name=\"postid\" value ="+postid+">\n"+
                    "   <input type=\"hidden\" id=\"odpowiedzid\" name=\"odpowiedzid\" value ="+odpowiedzid+">\n"+
                    "   <button type=\"submit\">Oceń</button>\n" +
                    "</form>");

            int asd = ocenka / 2 ;
            out.println("<style>\n" +
                    "  .rating {\n" +
                    "    display: flex;\n" +
                    "    flex-direction: row-reverse;\n" +
                    "  }\n" +
                    "  .rating > label {\n" +
                    "    color: #ffc107;\n" +
                    "    font-size: 50px;\n" +
                    "    position: relative;\n" +
                    "  }\n" +
                    "  .rating > label:before {\n" +
                    "    content: '\\2606';\n" +
                    "}\n" +
                    "</style>\n" +
                    "<div class=\"rating\">\n" +
                    "  <label style=\"color: #ffc107;\" class=\"star"+((3+postid+(odpowiedzid+5))*5)+"\"></label>\n" +
                    "  <label style=\"color: #ffc107;\" class=\"star"+((3+postid+(odpowiedzid+5))*5-1)+"\"></label>\n" +
                    "  <label style=\"color: #ffc107;\" class=\"star"+((3+postid+(odpowiedzid+5))*5-2)+"\"></label>\n" +
                    "  <label style=\"color: #ffc107;\" class=\"star"+((3+postid+(odpowiedzid+5))*5-3)+"\"></label>\n" +
                    "  <label style=\"color: #ffc107;\" class=\"star"+((3+postid+(odpowiedzid+5))*5-4)+"\"></label>\n" +
                    "</div>\n" +
                    "\n" +
                    "<script>\n" +
                    "var rat = "+asd+";\n" +
                    " rat = "+asd+";\n" +
                            "for(let i="+((3+postid+(odpowiedzid+5))*5-4)+";i<="+((3+postid+(odpowiedzid+5))*5)+";i++){\n" +
                            "    if(rat==0){\n" +
                    "var x = document.getElementsByClassName('star'+i);\n" +
                    "for( var q = 0; q < x.length; q++ ) {\n" +
                    "    x[q].style.color='gray';\n" +
                            "    }\n" +
                             "    }\n" +
                            "    else{\n" +
                            "        rat--;\n" +
                            "var x = document.getElementsByClassName('star'+i);\n" +
                            "for( var w = 0; w < x.length; w++ ) {\n" +
                            "    x[w].style.color='gold';\n" +
                            "}\n"+
                            "    }\n" +
                            "    \n" +
                            "}\n" +
                    "  </script>");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
