package com.example.myapplication;;


import java.util.Scanner;

public class Categoria {

    /* attributi della classe categoria */
    private String nome;   // nome della categoria
    private String color;  // colore da assegnare
    private int icon;   // id dell' icona da scegliere in drawable

    public Categoria(String nome, String color, int icon) {
        this.nome = nome;
        this.color = color;
        this.icon = icon;
    }

    public int getIcon() { return icon; }

    public void setIcon(int icon) {  this.icon = icon; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNome() {
        return nome;
    }

    public String getColor() {
        return color;
    }



}
