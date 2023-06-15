package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class Partido extends AppCompatActivity {
    SQLiteHelper helper;
    SQLiteDatabase db;
    TextView Equipo1,Equipo2,Goles,Minutos;
    ImageView imgEquipo1,imgEquipo2,imgGol,imgParada;
    public int minutos;

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

        /*try {
            Thread.sleep(2000); // Espera durante 2000 milisegundos (2 segundos)
            simularPartido();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
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
        minutos=Integer.parseInt(Minutos.getText().toString());
        float valoracion1=0,valoracion2=0;
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
        while (minutos < 90) {
            float finalValoracion = valoracion1;
            float finalValoracion1 = valoracion2;
            new CountDownTimer(30000, 2000){
                public void onTick(long millisUntilFinished){
                    marcarGol(finalValoracion, finalValoracion1);
                    minutos+=5;
                    Minutos.setText(String.valueOf(minutos));
                }
                public  void onFinish(){
                }
            }.start();

        }

    }

    private void marcarGol(float valoracion1, float valoracion2) {
        Cursor cursor2 =
                db.query(EstructuraBBDD.Partido.TABLE_NAME_PARTIDO, null,
                        null, null, null, null, null);
        cursor2.moveToLast();
        ContentValues values = new ContentValues();
        String equipo1 = cursor2.getString(1);
        String equipo2 = cursor2.getString(2);
        int goles1 = cursor2.getInt(3);
        int goles2 = cursor2.getInt(4);
        if(valoracion1 > valoracion2){
            if (Math.random() < 0.15) {
                goles1+=1;
                Goles.setText(goles1+" - "+goles2);
                values.put("goles1",goles1);
                db.update("partido",values,"equipo1 == ?",new String[]{equipo1});
            }
            if (Math.random() < 0.10) {
                goles2+=1;
                Goles.setText(goles1+" - "+goles2);
                values.put("goles2",goles2);
                db.update("partido",values,"equipo2 == ?",new String[]{equipo2});

            }
            if(Math.random() >0.95){
                Intent i = new Intent(this,Penalti.class);
                startActivity(i);
                try {
                    Thread.sleep(5000);// Espera durante 2000 milisegundos (2 segundos)

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }else if(valoracion2 > valoracion1){
            if (Math.random() < 0.10) {
                goles1+=1;
                Goles.setText(goles1+" - "+goles2);
                values.put("goles1",goles1);
                db.update("partido",values,"equipo1 == ?",new String[]{equipo1});
            }
            if (Math.random() < 0.15) {
                goles2+=1;
                Goles.setText(goles1+" - "+goles2);
                values.put("goles2",goles2);
                db.update("partido",values,"equipo2 == ?",new String[]{equipo2});
            }
            if(Math.random() >0.95){
                Intent i = new Intent(this,Penalti.class);
                startActivity(i);
                try {
                    Thread.sleep(5000); // Espera durante 2000 milisegundos (2 segundos)

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }else{
            if (Math.random() < 0.15) {
                goles1+=1;
                Goles.setText(goles1+" - "+goles2);
                values.put("goles1",goles1);
                db.update("partido",values,"equipo1 == ?",new String[]{equipo1});
            }
            if (Math.random() < 0.15) {
                goles2+=1;
                Goles.setText(goles1+" - "+goles2);
                values.put("goles2",goles2);
                db.update("partido",values,"equipo2 == ?",new String[]{equipo2});
            }
            if(Math.random() >0.95){
                Intent i = new Intent(this,Penalti.class);
                startActivity(i);
                try {
                    Thread.sleep(5000); // Espera durante 2000 milisegundos (2 segundos)

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }



}