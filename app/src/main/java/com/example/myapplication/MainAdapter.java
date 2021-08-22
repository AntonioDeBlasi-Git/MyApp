package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainAdapter extends BaseAdapter implements PopupMenu.OnMenuItemClickListener  {

    private Context context;
    private LayoutInflater inflater;
    TextView textView_importo;
    private List<Movimenti> lista_movimenti;
    private int index;
    Notice_change notice_change;


    public MainAdapter(Context c, List<Movimenti> l,Notice_change m){
        this.context=c;
        this.lista_movimenti=l;
        this.notice_change = m;
    }

    @Override
    public int getCount() {
        return lista_movimenti.size();
    }

    @Override
    public Movimenti getItem(int position) {
        return lista_movimenti.get(position);
    }

    public void addItem(Movimenti o){
        lista_movimenti.add(o);
    }

    public List<Movimenti> getLista(){ return lista_movimenti;}

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.gridview_element_layout, null);
        }

        TextView textView_nome = convertView.findViewById(R.id.titolo_elemento);
        FloatingActionButton floatingActionButton = convertView.findViewById(R.id.bottone_elemento);
        textView_importo = convertView.findViewById(R.id.Spesa_elemento);

        textView_nome.setText(lista_movimenti.get(position).getTipo().getNome());

       String spesa = lista_movimenti.get(position).getImporto_totale()+" $";

       textView_importo.setText(spesa);


      floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(lista_movimenti.get(position).getTipo().getColor())));
      floatingActionButton.setImageResource(lista_movimenti.get(position).getTipo().getIcon());

      floatingActionButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Intent intent = new Intent(context.getApplicationContext(),Creazione_spesa.class);


              intent.putExtra("nome",lista_movimenti.get(position).getTipo().getNome());
              intent.putExtra("immagine",lista_movimenti.get(position).getTipo().getIcon());
              intent.putExtra("colore",lista_movimenti.get(position).getTipo().getColor());
              intent.putExtra("posizione", position);


              ((Activity) context).startActivityForResult(intent,1);

          }

      });

      floatingActionButton.setOnLongClickListener(new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View view) {
              index=position;
              showPopup(view);
              return false;
          }
      });

        return convertView;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 && resultCode == -1 ) {

            int pos = data.getIntExtra("posizione", 0);  //questo funzione e mi da la posizione corretta

            lista_movimenti.get(pos).aggiungi_spesa(new Spesa(data.getStringExtra("tipo"),data.getStringExtra("importo"), data.getStringExtra("data"), data.getStringExtra("commento")));

        }

    }

    public void showPopup(View v){
        PopupMenu popupMenu = new PopupMenu(context.getApplicationContext(), v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item_1:

                lista_movimenti.remove(index);
                notifyDataSetChanged();
                notice_change.notifica();

                return true;
            case R.id.item_2:

                UtilityClass.getInstance().setList(lista_movimenti);
                Intent intent = new Intent(context.getApplicationContext(),Spese_per_categoria.class);
                intent.putExtra("index",index);
                context.startActivity(intent);

                return true;
            default:
                return false;
        }
    }
}
