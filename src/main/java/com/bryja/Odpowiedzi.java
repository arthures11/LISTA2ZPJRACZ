package com.bryja;

import java.io.Serializable;

public class Odpowiedzi implements Serializable {
    String author;
    String tresc;
    String data;

    public int ocena;
    public int ilosc_ocen=0;
    public int suma_ocen=0;

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
        this.author = aut;
        this.tresc = tr;
        this.data = dat;
    }
}
