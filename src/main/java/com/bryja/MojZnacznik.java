package com.bryja;

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
        try {
            FileInputStream fi = new FileInputStream(new File("posts.ser"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            try {
                posty = (List<Post>) oi.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            oi.close();
            fi.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
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
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
