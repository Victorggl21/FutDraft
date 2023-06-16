package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class Partido extends AppCompatActivity {
    SQLiteHelper helper;
    SQLiteDatabase db;
    Button volver;
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
        Glide.with(this).asGif().load(R.drawable.gol).into(imgGol);
        imgParada = findViewById(R.id.imageView6);
        Glide.with(this).asGif().load(R.drawable.win).into(imgParada);
        volver = findViewById(R.id.button);
        imgGol.setVisibility(View.INVISIBLE);
        imgParada.setVisibility(View.INVISIBLE);
        volver.setAlpha(0);
        volver.setEnabled(false);
        mostrarPartido();
    }

    @Override
    protected void onResume() {
        super.onResume();
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
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
            float finalValoracion = valoracion1;
            float finalValoracion1 = valoracion2;
            new CountDownTimer(19000, 1000){
                public void onTick(long millisUntilFinished){
                    if(minutos<90) {
                        marcarGol(finalValoracion, finalValoracion1);
                        minutos += 5;
                        Minutos.setText(String.valueOf(minutos));
                    }
                }
                public  void onFinish(){
                    Cursor cursor2 =
                            db.query(EstructuraBBDD.Partido.TABLE_NAME_PARTIDO,null,
                                    null,null,null,null,null);
                    cursor2.moveToLast();
                    String equipo1 = cursor2.getString(1);
                    String equipo2 = cursor2.getString(2);
                    int goles1 = cursor2.getInt(3);
                    int goles2 = cursor2.getInt(4);
                    db.delete("equipo","nombre == ?",new String[]{equipo2});
                    Cursor cursor3 =
                            db.query(EstructuraBBDD.Equipo.TABLE_NAME_EQUIPO,null,
                                    null,null,null,null,null);
                    cursor3.moveToFirst();
                    String nuevoEquipo = cursor3.getString(1);
                    if(minutos==90) {
                        if (goles1 > goles2) {

                            ContentValues values = new ContentValues();
                            values.put("equipo1", equipo1);
                            values.put("equipo2", nuevoEquipo);
                            values.put("goles1", 0);
                            values.put("goles2", 0);
                            if(!equipo1.equals(nuevoEquipo)) {
                                db.insert("partido", null, values);
                            }
                            imgParada.setVisibility(View.VISIBLE);
                            volver.setEnabled(true);
                            volver.setAlpha(1);
                        } else if (goles2 > goles1) {
                            Glide.with(getApplicationContext()).asGif().load(R.drawable.lose).into(imgParada);
                            imgParada.setVisibility(View.VISIBLE);
                            volver.setEnabled(true);
                            volver.setAlpha(1);
                        } else {
                            Intent i = new Intent(getApplicationContext(), Penalti.class);
                            startActivity(i);
                                    volver.setEnabled(true);
                                    volver.setAlpha(1);
                        }
                    }
                }
            }.start();


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
                new CountDownTimer(1000, 1000){
                    public void onTick(long millisUntilFinished){
                            imgGol.setVisibility(View.VISIBLE);
                    }
                    public  void onFinish(){
                        imgGol.setVisibility(View.INVISIBLE);
                    }
                }.start();

            }
            if (Math.random() < 0.10) {
                goles2+=1;
                Goles.setText(goles1+" - "+goles2);
                values.put("goles2",goles2);
                db.update("partido",values,"equipo2 == ?",new String[]{equipo2});
                new CountDownTimer(1000, 1000){
                    public void onTick(long millisUntilFinished){
                        imgGol.setVisibility(View.VISIBLE);
                    }
                    public  void onFinish(){
                        imgGol.setVisibility(View.INVISIBLE);
                    }
                }.start();
            }
        }else if(valoracion2 > valoracion1){
            if (Math.random() < 0.10) {
                goles1+=1;
                Goles.setText(goles1+" - "+goles2);
                values.put("goles1",goles1);
                db.update("partido",values,"equipo1 == ?",new String[]{equipo1});
                new CountDownTimer(1000, 1000){
                    public void onTick(long millisUntilFinished){
                        imgGol.setVisibility(View.VISIBLE);
                    }
                    public  void onFinish(){
                        imgGol.setVisibility(View.INVISIBLE);
                    }
                }.start();
            }
            if (Math.random() < 0.15) {
                goles2+=1;
                Goles.setText(goles1+" - "+goles2);
                values.put("goles2",goles2);
                db.update("partido",values,"equipo2 == ?",new String[]{equipo2});
                new CountDownTimer(1000, 1000){
                    public void onTick(long millisUntilFinished){
                        imgGol.setVisibility(View.VISIBLE);
                    }
                    public  void onFinish(){
                        imgGol.setVisibility(View.INVISIBLE);
                    }
                }.start();
            }
        }else{
            if (Math.random() < 0.15) {
                goles1+=1;
                Goles.setText(goles1+" - "+goles2);
                values.put("goles1",goles1);
                db.update("partido",values,"equipo1 == ?",new String[]{equipo1});
                new CountDownTimer(1000, 1000){
                    public void onTick(long millisUntilFinished){
                        imgGol.setVisibility(View.VISIBLE);
                    }
                    public  void onFinish(){
                        imgGol.setVisibility(View.INVISIBLE);
                    }
                }.start();
            }
            if (Math.random() < 0.15) {
                goles2+=1;
                Goles.setText(goles1+" - "+goles2);
                values.put("goles2",goles2);
                db.update("partido",values,"equipo2 == ?",new String[]{equipo2});
                new CountDownTimer(1000, 1000){
                    public void onTick(long millisUntilFinished){
                        imgGol.setVisibility(View.VISIBLE);
                    }
                    public  void onFinish(){
                        imgGol.setVisibility(View.INVISIBLE);
                    }
                }.start();
            }
        }
    }


    public void volver(View view) {
        Intent i = new Intent(this,Torneo.class);
        startActivity(i);
    }
}