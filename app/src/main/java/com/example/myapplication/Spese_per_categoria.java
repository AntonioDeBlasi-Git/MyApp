package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
/** CLASSE SPESE PER CATEGORIA
 * Stesso funzionamento di lista spese solo permette di vedere le spese in riferimento ad una sola categoria
 * Progetto: De Blasi Antonio e Zampirollo Francesco OOP
 * */
public class Spese_per_categoria extends AppCompatActivity {

    private List<Movimenti> movimentiList;
    private List<Spesa> spesaList;
    private int index;
    private ListView listView;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spese_categoria);

        movimentiList= new ArrayList<>();
        spesaList = new ArrayList<>();
        movimentiList = UtilityClass.getInstance().getList();



        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        spesaList = movimentiList.get(index).getLista();
        listView = findViewById(R.id.lista_spese_categoria);
        adapter = new ListAdapter(this,spesaList);
        listView.setAdapter(adapter);
    }
}