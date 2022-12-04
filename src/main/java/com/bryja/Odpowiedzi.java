package com.bryja;

import java.io.Serializable;

public class Odpowiedzi implements Serializable {
    String author;
    String tresc;
    String data;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    Odpowiedzi(String tr, String aut, String dat){
        this.author = aut;
        this.tresc = tr;
        this.data = dat;
    }
}
