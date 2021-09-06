package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

/** CLASSE UTILITYCLASS
 * Classe utilizzata per memorizzare i vettori di movimenti e passarli da un activity all'altra
 * Progetto: De Blasi Antonio e Zampirollo Francesco OOP
 * */

public class UtilityClass {


        private UtilityClass(){};
        private static UtilityClass instance;
        private List<Movimenti> list;


        public List<Movimenti> getList() {
            return list;
        }

        public void setList(List<Movimenti> list) {
            this.list = list;
        }

        public static UtilityClass getInstance(){
            if(instance == null){
                instance = new UtilityClass();
            }
            return instance;
        }
}
