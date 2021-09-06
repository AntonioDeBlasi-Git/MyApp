package com.example.myapplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/** CLASSE MOVIMENTI
 * la classe movimenti, memorizza tutte le spese in relazione ad una determinata categoria,
 * tramite una lista di oggetti spesa, inoltre memorizza l'importo totale come somma delle varie spese
 * Progetto: De Blasi Antonio e Zampirollo Francesco OOP
 * */



public class Movimenti {

    private Categoria tipo;
    private List<Spesa> lista;
    private float importo_totale;

    //un movimento viene creato quando creo una nuova categoria, pertanto nel costruttore passo una categoria, mentre gi altri valore vengono settati a dei default base
    public Movimenti(Categoria tipo){
        this.tipo = tipo;
        this.lista = new ArrayList<Spesa>();  //inizializzo la lista con un array list
        this.importo_totale = 0;             //inizializzo l'importo a 0 alla creazione
    }


    /* metodo che richiamo per aggiungere una categoria e quando tolgo un elemnto da un altra lista di movimenti */
    public void aggiungi_spesa(Spesa s){
        lista.add(s);
        importo_totale += Float.parseFloat(s.getImporto());
    }

    public void elimina_spesa(Spesa s){
        lista.remove(s);
        importo_totale -= Float.parseFloat(s.getImporto());
    }

    public Categoria getTipo() {
        return tipo;
    }

    public void setTipo(Categoria tipo) {
        this.tipo = tipo;
    }

    public List<Spesa> getLista() {
        return lista;
    }

    public void setLista(List<Spesa> lista) {
        this.lista = lista;
    }

    public float getImporto_totale() {
        return importo_totale;
    }

    public void setImporto_totale(float importo_totale) {
        this.importo_totale = importo_totale;
    }
}
