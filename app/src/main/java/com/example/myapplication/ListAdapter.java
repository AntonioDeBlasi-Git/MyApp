package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private List<Spesa> lista_spesa;
    private Context context;
    private LayoutInflater inflater;

    public ListAdapter(Context c, List<Spesa> l){
        this.context=c;
        this.lista_spesa=l;
    }

    @Override
    public int getCount() {
        return lista_spesa.size();
    }

    @Override
    public Spesa getItem(int position) {
        return lista_spesa.get(position);
    }

    public void addItem(Spesa o){
        lista_spesa.add(o);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.listview_element_layout, null);
        }
        LinearLayout linearLayout = convertView.findViewById(R.id.linear_element_layout);
        TextView categoria = convertView.findViewById(R.id.alla_categoria);
        TextView data = convertView.findViewById(R.id.all_data);
        TextView commento = convertView.findViewById(R.id.all_commento);



                String category = "  Alla Categoria : " + lista_spesa.get(position).getTipo()+ "   " + lista_spesa.get(position).getImporto()+" $";
                String date = "  In data   " + lista_spesa.get(position).getData();
                String comment = "  "+lista_spesa.get(position).getCommento();
                categoria.setText(category);
                data.setText(date);
                commento.setText(comment);




        return convertView;
    }
    }
