package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

/** CLASSE MYPIECHART
 * classe che implementa il piechart tramite l'apposita classe
 * Progetto: De Blasi Antonio e Zampirollo Francesco OOP
 * */


public class MyPieChart extends AppCompatActivity {

    private PieChart pieChart;
    private List<Movimenti> lista_movimenti;
    private float saldo;
    private TextView textView;
    private String saldo_totale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.andamento);

        saldo = (float)0;
        textView = findViewById(R.id.testo_saldo);
        lista_movimenti = new ArrayList<>();
        lista_movimenti = UtilityClass.getInstance().getList();

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

                        UtilityClass.getInstance().setList(lista_movimenti);
                        startActivity(new Intent(getApplicationContext(),Lista_Spese.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.andamento:


                        return true;
                }
                return false;
            }
        });

        pieChart = findViewById(R.id.activity_main_piechart);
        setupPieChart();
        loadPieChartData();
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Categorie di Spesa");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

      /*  Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);*/
    }

    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();


        ArrayList<Integer> colors = new ArrayList<>();
        for(Movimenti o : lista_movimenti){
            if(o.getImporto_totale() != 0){
                entries.add(new PieEntry(o.getImporto_totale(),o.getTipo().getNome()));
                colors.add(Color.parseColor(o.getTipo().getColor()));
                saldo += o.getImporto_totale();
            }
        }

        saldo_totale = "Spesa complessiva  "+ String.valueOf(saldo) + " €";
        textView.setText(saldo_totale);

        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}

