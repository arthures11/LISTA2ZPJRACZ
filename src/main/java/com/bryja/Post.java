package com.bryja;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="post")
public class Post{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String pytanie;

    public String author;
    public String data;
    int liczba_odpowiedzi;

    @OneToMany(targetEntity=Odpowiedzi.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "post")
    public List<Odpowiedzi> odps = new ArrayList<Odpowiedzi>();


    public Post(Integer id, String pytanie, String author, String data, int liczba_odpowiedzi, List<Odpowiedzi> odps) {
        this.id = id;
        this.pytanie = pytanie;
        this.author = author;
        this.data = data;
        this.liczba_odpowiedzi = liczba_odpowiedzi;
        this.odps = odps;
    }


    public String getPytanie() {
        return pytanie;
    }

    public void setPytanie(String pytanie) {
        this.pytanie = pytanie;
    }

    public Post(){}

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Odpowiedzi> getOdps() {
        return odps;
    }

    public void setOdps(List<Odpowiedzi> odps) {
        this.odps = odps;
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



    public Post(int id, String pytanie, String author, String data, int liczba_odpowiedzi) {
        this.pytanie = pytanie;
        this.author = author;
        this.data = data;
        this.liczba_odpowiedzi = liczba_odpowiedzi;
        this.id=id;
    }
}
