package com.example.myapplication;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Dialog_view_adapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private int[] icon_id;
    private int id;
    private IconChangeListener iconChangeListener;
    private FloatingActionButton my_fab;

    public Dialog_view_adapter(Context c , int [] v, IconChangeListener a ){
        this.context=c;
        this.icon_id=v;
        this.iconChangeListener = a;
    }

    @Override
    public int getCount() {
        return icon_id.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.dialog_gridview_element, null);
        }

        FloatingActionButton floatingActionButton = convertView.findViewById(R.id.bottone_icona);
        floatingActionButton.setImageResource(icon_id[position]);
        my_fab = convertView.findViewById(R.id.but_center_icon);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconChangeListener.onIconChange(icon_id[position]);
            }
        });

        return convertView;
    }
}
