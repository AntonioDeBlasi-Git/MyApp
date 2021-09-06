package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

/** CLASSE LISTA SPESE
 * in questa classe creiamo una lista contenente tutte le spese per categoria in questo modo diamo
 * la possibiltà all'utente di visionare soloo l'elenco di tutte le spese
 * Progetto: De Blasi Antonio e Zampirollo Francesco OOP
 * */

public class Lista_Spese extends AppCompatActivity {

    private ListView listView;
    private List<Movimenti> lista_movimenti;
    private ListAdapter adapter;
    private List<Spesa> lista_spese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_spese);
        lista_spese = new ArrayList<>();
        listView = findViewById(R.id.listview);

        lista_movimenti = new ArrayList<>();
        lista_movimenti = UtilityClass.getInstance().getList();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.movimenti);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.categorie:
                        UtilityClass.getInstance().setList(lista_movimenti);
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.movimenti:

                        return true;

                    case R.id.andamento:
                        UtilityClass.getInstance().setList(lista_movimenti);
                        startActivity(new Intent(getApplicationContext(),MyPieChart.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        //ovviamente non ci interessano i movimenti con importo uguale a zero, poichè privi di spese
        for(Movimenti o : lista_movimenti){
            if(o.getImporto_totale() != 0){
                for(Spesa a : o.getLista()){
                    lista_spese.add(a);
                }
            }
        }

        adapter = new ListAdapter(this,lista_spese);
        listView.setAdapter(adapter);


    }



}