package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class Partido extends AppCompatActivity {
    SQLiteHelper helper;
    SQLiteDatabase db;
    TextView Equipo1,Equipo2,Goles,Minutos;
    ImageView imgEquipo1,imgEquipo2,imgGol,imgParada;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido);
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        Equipo1 = findViewById(R.id.txtEquipo1);
        Equipo2 = findViewById(R.id.txtEquipo2);
        Goles = findViewById(R.id.txtResultado);
        Minutos = findViewById(R.id.txtMinuto);
        imgEquipo1 = findViewById(R.id.imageView3);
        imgEquipo2 = findViewById(R.id.imageView4);
        imgGol = findViewById(R.id.imageView5);
        imgParada = findViewById(R.id.imageView6);

        mostrarPartido();
        simularPartido();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    private void mostrarPartido(){
        Cursor cursor =
                db.query(EstructuraBBDD.Partido.TABLE_NAME_PARTIDO, null,
                        null, null, null, null, null);
        cursor.moveToLast();
        String equipo1 = cursor.getString(1);
        String equipo2 = cursor.getString(2);
        int goles1 = cursor.getInt(3);
        int goles2 = cursor.getInt(4);
        Equipo1.setText(equipo1);
        Equipo2.setText(equipo2);
        Goles.setText(goles1+" - "+goles2);

        Cursor cursor2 =
                db.query(EstructuraBBDD.Equipo.TABLE_NAME_EQUIPO, null,
                        null, null, null, null, null);
        cursor2.moveToFirst();
        while(!cursor2.isAfterLast()){
            String equipo = cursor2.getString(1);
            int escudo = cursor2.getInt(2);
            if(equipo.equals(equipo1)){
                imgEquipo1.setImageResource(escudo);
            }
            if(equipo.equals(equipo2)){
                imgEquipo2.setImageResource(escudo);
            }
            cursor2.moveToNext();
        }
    }

    public void simularPartido(){
        int minutos=Integer.parseInt(Minutos.getText().toString());
        float valoracion1=0,valoracion2=0;
        while (minutos < 90) {
            Cursor cursor =
                    db.query(EstructuraBBDD.Equipo.TABLE_NAME_EQUIPO,null,
                            null,null,null,null,null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String nombre= cursor.getString(1);
                float valoracion = cursor.getFloat(3);
                if(nombre.equals(Equipo1.getText().toString())){
                    valoracion1=valoracion;
                }
                if(nombre.equals(Equipo2.getText().toString())){
                    valoracion2=valoracion;
                }
                cursor.moveToNext();
            }
            marcarGol(valoracion1, valoracion2);
            // Simular goles con cierta probabilidad en cada minuto

            Minutos.setText(String.valueOf(minutos+5));
        }
    }

    private void marcarGol(float valoracion1, float valoracion2) {
        Cursor cursor2 =
                db.query(EstructuraBBDD.Partido.TABLE_NAME_PARTIDO, null,
                        null, null, null, null, null);
        cursor2.moveToLast();
        int goles1 = cursor2.getInt(3);
        int goles2 = cursor2.getInt(4);
        if(valoracion1 > valoracion2){
            if (Math.random() < 0.15) {
                goles1+=1;
                Goles.setText(goles1+" - "+goles2);

            }
            if (Math.random() < 0.10) {
                goles2+=1;
                Goles.setText(goles1+" - "+goles2);

            }
        }else if(valoracion2 > valoracion1){
            if (Math.random() < 0.10) {
                goles1+=1;
                Goles.setText(goles1+" - "+goles2);

            }
            if (Math.random() < 0.15) {
                goles2+=1;
                Goles.setText(goles1+" - "+goles2);

            }
        }else{
            if (Math.random() < 0.15) {
                goles1+=1;
                Goles.setText(goles1+" - "+goles2);

            }
            if (Math.random() < 0.15) {
                goles2+=1;
                Goles.setText(goles1+" - "+goles2);

            }
        }
    }



}