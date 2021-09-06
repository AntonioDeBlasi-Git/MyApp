package com.example.myapplication;;


import java.util.Scanner;

/**  CLASSE CATEGORIA
 * Composta da tre attributi principali, nome, colore e icona.
 * Rappresenta l'elemento di base di tutta l'appicazione di gestione monetaria, in quanto tutte le spese sono
 * associate a delle categorie.
 * Progetto: De Blasi Antonio e Zampirollo Francesco OOP
 * */

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
