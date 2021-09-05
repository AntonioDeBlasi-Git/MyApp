package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Notice_change {


    GridView gridView;

    private List<Categoria> lista_categorie;
    private List<Movimenti> lista_movimenti;
    private MainAdapter adapter;
    private Spesa curr;

    int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gridView = findViewById(R.id.grid_view);

        loadData();

        adapter = new MainAdapter(MainActivity.this, lista_movimenti, this);

        gridView.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.categorie);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                        case R.id.categorie:

                            return true;

                        case R.id.movimenti:

                            UtilityClass.getInstance().setList(adapter.getLista());
                            startActivity(new Intent(getApplicationContext(),Lista_Spese.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.andamento:

                            UtilityClass.getInstance().setList(adapter.getLista());
                            startActivity(new Intent(getApplicationContext(),MyPieChart.class));
                            overridePendingTransition(0,0);
                            return true;
                }
                return false;
            }
        });


    }

    public void SaveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(lista_movimenti);
        editor.putString("lista movimenti",json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("lista movimenti",null);
        Type type = new TypeToken<ArrayList<Movimenti>>() {}.getType();

        lista_movimenti = gson.fromJson(json,type);
        //lista_movimenti = Set_Default();
        //SaveData();
      if(lista_movimenti == null){
            lista_movimenti = Set_Default();
        }
    }

    public List<Movimenti> Set_Default(){
        /* inizializzazione categorie di default */

        lista_categorie = new ArrayList<>();
        lista_categorie.add( new Categoria("Alimentari", "#00ccff",R.drawable.ic_alimenti));
        lista_categorie.add( new Categoria("Ristorante", "#ff66ff",R.drawable.ic_ristorante));
        lista_categorie.add( new Categoria("Tempo libero", "#ff9900",R.drawable.ic_tempo_libero));
        lista_categorie.add( new Categoria("Trasporto", "#00cc00",R.drawable.ic_trasporto));
        lista_categorie.add( new Categoria("Salute", "#ff0000",R.drawable.ic_salute));
        lista_categorie.add( new Categoria("Regali", "#0000ff",R.drawable.ic_regali));
        lista_categorie.add( new Categoria("Famiglia", "#ffff66",R.drawable.ic_famiglia));
        lista_categorie.add( new Categoria("Shopping", "#9966ff",R.drawable.ic_shopping));

        /* inizializiamo i movimenti di default */

        List<Movimenti> list = new ArrayList<>();

        for(Categoria o : lista_categorie){
            list.add(new Movimenti(o));
        }

        return list;
    }

    public void Aggiungi_categoria(View v) {
        open_Creazione_Categorie();
    }



    public void open_Creazione_Categorie(){
        Intent intent = new Intent(this,Creazione_Categorie.class);
        startActivityForResult(intent,0);
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == -1) {
            // da qui posso recuperare le info spesa
            pos = data.getIntExtra("posizione", 0);
            adapter.onActivityResult(requestCode, resultCode, data);
            gridView.setAdapter(adapter);
        }

        if(requestCode == 0 && resultCode == -1){
            Categoria a = new Categoria(data.getStringExtra("nome"),data.getStringExtra("colore"),data.getIntExtra("id_icon",0));
            adapter.addItem(new Movimenti(a));
            lista_movimenti = adapter.getLista();
            gridView.setAdapter(adapter);
        }

        lista_movimenti = adapter.getLista();
        SaveData();
    }


    @Override
    public void notifica() {
        SaveData();
    }
}