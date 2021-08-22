package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

public class Creazione_Categorie extends AppCompatActivity implements IconChangeListener {

    RelativeLayout mainlayout;
    TextView textView;
    EditText editText;
    String hex_color;
    FloatingActionButton fab;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creazione_categorie);
        mainlayout = (RelativeLayout) findViewById(R.id.my_layout);
        textView = findViewById(R.id.text_cat);
        editText = findViewById(R.id.nome_categoria);
        fab = findViewById(R.id.but_center_icon);
        id = R.drawable.ic_bici;
        hex_color = "#9966ff";
    }



    public void cancella(View a){
        super.onBackPressed();
    }

    public void salva(View b){

        Intent intent = new Intent();
        intent.putExtra("id_icon", id);
        intent.putExtra("colore", hex_color);
        intent.putExtra("nome", editText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();

    }


    public void Scegli_colore(View a){
        opencolorpicker();
    }

    public void Scegli_icona(View a){
        openDialog();
    }

    public void Torna_main(View v) {
        super.onBackPressed();
    }


    public void openDialog(){
        Icon_dialog icon_dialog = new Icon_dialog(this);
        
        icon_dialog.show(getSupportFragmentManager(),"Icon Dialog");
    }


    public void opencolorpicker(){
        final ColorPicker colorPicker = new ColorPicker(this);
        ArrayList<String> colori = new ArrayList<>();
        colori.add("#258174");
        colori.add("#3C8D2F");
        colori.add("#20724f");
        colori.add("#6a3ab2");
        colori.add("#323299");
        colori.add("#800080");
        colori.add("#b79716");
        colori.add("#966d37");
        colori.add("#b77231");
        colori.add("#808000");
        colori.add("#00ccff");
        colori.add("#ff66ff");
        colori.add("#ff9900");
        colori.add("#00cc00");
        colori.add("#ff0000");
        colori.add("#0000ff");
        colori.add("#ffff66");
        colori.add("#9966ff");


        colorPicker.setColors(colori)
                .setColumns(5)
                .setRoundColorButton(true)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        mainlayout.setBackgroundColor(color);
                        String hex = Integer.toHexString(color);
                        hex_color = "#"+hex.substring(2);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).show();
    }

    @Override
    public void onIconChange(int id) {
        fab.setImageResource(id);
        this.id=id;
    }
}