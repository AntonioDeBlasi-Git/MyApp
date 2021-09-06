package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/** CLASSE ICON DIALOG
 * Implementazione vera e propria dell'icon dialog per permettere all'utente di scegliere un icona
 * Progetto: De Blasi Antonio e Zampirollo Francesco OOP
 * */

public class Icon_dialog extends AppCompatDialogFragment implements IconChangeListener{

    FloatingActionButton fab; //da collegare al layout
    FloatingActionButton fab_2;
    IconChangeListener iconChangeListener;
    int id;

    GridView gridView;
    //vettore di icone di dafault
    private int[] icon_id = {
            R.drawable.ic_alimenti,R.drawable.ic_ristorante,R.drawable.ic_tempo_libero,R.drawable.ic_trasporto,R.drawable.ic_salute, R.drawable.ic_regali,
            R.drawable.ic_famiglia,R.drawable.ic_shopping, R.drawable.ic_game,R.drawable.ic_spa,R.drawable.ic_burger,R.drawable.ic_aereo,R.drawable.ic_vasca,
            R.drawable.ic_pennello,R.drawable.ic_negozietto,R.drawable.ic_musica,R.drawable.ic_movimenti,R.drawable.ic_moto,R.drawable.ic_macchina,
            R.drawable.ic_manubrio,R.drawable.ic_love,R.drawable.ic_letto, R.drawable.ic_casco,R.drawable.ic_bici,R.drawable.ic_bbq,R.drawable.ic_bask,R.drawable.ic_ape
    };

    //il costruttore della classe icon dialog prende come parametro un oggetto icl in modo tale da poter notificare
    //alla classe creazione categoria qual'è l'id dell'icona scelta dall'utente
    public Icon_dialog( IconChangeListener a){
        iconChangeListener = a;
    };


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /* creazione dialog */

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_icon_menu, null);

        builder.setView(view)
                .setTitle("Scegli Icona")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        iconChangeListener.onIconChange(id);
                    }
                });

        /* creazione gridview */
        gridView = view.findViewById(R.id.grid_view_dialog);

        Dialog_view_adapter adapter = new Dialog_view_adapter(getActivity(),icon_id,this);
        gridView.setAdapter(adapter);

        return builder.create();
    }

    //in questo modo salviamo l'id dell'elemento scelto nell'attributo id di questa classe
    @Override
    public void onIconChange(int id) {
        this.id=id;
    }
}
