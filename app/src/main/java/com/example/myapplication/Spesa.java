package com.example.myapplication;

import java.text.DateFormat;
import java.time.LocalDate;

/** CLASSE SPESA
 * Classe fondamentale utilizzata in lista spese e spese pere categoria.
 * Rappresenta le spese che possono essere associate ad una categoria, ed è composta da un tipo(categoria),
 * un importo, una data e un commento.
 * Progetto: De Blasi Antonio e Zampirollo Francesco OOP
 * */


public class Spesa {

    private String tipo;
    private String importo;    //un importo che indica la spesa fatta
    private String data;   //data nella quale è stato effettuato
    private String commento;  //commento sull'acquisto appena effettuato

    public Spesa(String tipo,String importo, String data, String commento) {
        //ragionare su come impostare tali dati
        this.tipo = tipo;
        this.data = data;
        this.commento = commento;
        this.importo = importo;  //importo inserito da utente
    }

    public void aggiorna_importo(String importo){
        setImporto(importo);
    };

    public void aggiorna_commento(String commento){
        setCommento(commento);
    };

    public void aggiorna_data(String data){ setData(data); };


    public String getCommento() { return commento; }

    public void setCommento(String commento) { this.commento = commento;  }

    public String getImporto() {
        return importo;
    }

    public void setImporto(String importo) {
        this.importo = importo;
    }

    public String getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setData(String data) {
        this.data = data;
    }
}
