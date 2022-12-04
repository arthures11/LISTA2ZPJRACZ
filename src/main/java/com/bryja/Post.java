package com.bryja;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Post implements Serializable {
    int id;
    String pytanie;
    String author;
    String data;
    int liczba_odpowiedzi;
    List<Odpowiedzi> odps = new ArrayList<>();

    public String getPytanie() {
        return pytanie;
    }

    public void setPytanie(String pytanie) {
        this.pytanie = pytanie;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getLiczba_odpowiedzi() {
        return liczba_odpowiedzi;
    }

    public void setLiczba_odpowiedzi(int liczba_odpowiedzi) {
        this.liczba_odpowiedzi = liczba_odpowiedzi;
    }

    public List<Odpowiedzi> getOdps() {
        return odps;
    }

    public void setOdps(List<Odpowiedzi> odps) {
        this.odps = odps;
    }

    public Post(int id, String pytanie, String author, String data, int liczba_odpowiedzi) {
        this.pytanie = pytanie;
        this.author = author;
        this.data = data;
        this.liczba_odpowiedzi = liczba_odpowiedzi;
        this.id=id;
    }
}
