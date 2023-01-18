package com.bryja;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Odpowiedzi{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String author;
    public String tresc;
    public String data;

    public int ocena;
    public int ilosc_ocen=0;
    public int suma_ocen=0;

    public int PostID;

    public Odpowiedzi(int id, String author, String tresc, String data, int ocena, int ilosc_ocen, int suma_ocen, int postID) {
        this.id = id;
        this.author = author;
        this.tresc = tresc;
        this.data = data;
        this.ocena = ocena;
        this.ilosc_ocen = ilosc_ocen;
        this.suma_ocen = suma_ocen;
        this.PostID = postID;
    }

    // @ManyToOne(targetEntity=Post.class,fetch = FetchType.LAZY)
  //  private Post post;
    public int getIlosc_ocen() {
        return ilosc_ocen;
    }

    public int getSuma_ocen() {
        return suma_ocen;
    }

    public void setSuma_ocen(int suma_ocen) {
        this.suma_ocen = suma_ocen;
    }


    public void setIlosc_ocen(int ilosc_ocen) {
        this.ilosc_ocen = ilosc_ocen;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Odpowiedzi(Integer id, String author, String tresc, String data, int ocena, int ilosc_ocen, int suma_ocen, Post post) {
        this.id = id;
        this.author = author;
        this.tresc = tresc;
        this.data = data;
        this.ocena = ocena;
        this.ilosc_ocen = ilosc_ocen;
        this.suma_ocen = suma_ocen;
       // this.post = post;
    }

    public Odpowiedzi(){}
   // public Post getPost() {
   //     return post;
  // }

   // public void setPost(Post post) {
   //     this.post = post;
  //  }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    Odpowiedzi(String tr, String aut, String dat){
       // this.author = aut;
        this.tresc = tr;
        this.data = dat;
    }
}
