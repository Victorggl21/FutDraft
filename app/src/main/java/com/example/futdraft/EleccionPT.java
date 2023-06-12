package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class EleccionPT extends AppCompatActivity implements View.OnClickListener{

    SQLiteHelper helper;
    SQLiteDatabase db;
    ArrayList <Integer> portero = new ArrayList<>();


    int n1=0,n2=0, n3=0, n4=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_pt);
        jugadores();
    }

    public void jugadores(){
        Random random = new Random();

        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        Cursor cursor =
                db.query(EstructuraBBDD.Jugador.TABLE_NAME_JUGADOR, null,
                        null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String posicion = cursor.getString(2);
            int foto = cursor.getInt(5);
            if (posicion.equals("PT")) {
                portero.add(foto);
            }
            cursor.moveToNext();
        }
        db.close();
        do {
            n1 = random.nextInt(portero.size());
            n2 = random.nextInt(portero.size());
            n3 = random.nextInt(portero.size());
            n4 = random.nextInt(portero.size());
        } while (n1 == n2 || n1 == n3 || n1 == n4 || n2 == n3 || n2 == n4 || n3 == n4);

        ImageView img1 = findViewById(R.id.imageButton55);
        img1.setImageResource(portero.get(n1));
        ImageView img2 = findViewById(R.id.imageButton56);
        img2.setImageResource(portero.get(n2));
        ImageView img3 = findViewById(R.id.imageButton57);
        img3.setImageResource(portero.get(n3));
        ImageView img4 = findViewById(R.id.imageButton58);
        img4.setImageResource(portero.get(n4));
    }

    public void volveralineacion(View view) {
        onBackPressed();
        consultaEleccion(portero.get(n1));
    }
    public void volveralineacion2(View view) {
        onBackPressed();
        consultaEleccion(portero.get(n2));
    }
    public void volveralineacion3(View view) {
        onBackPressed();
        consultaEleccion(portero.get(n3));
    }
    public void volveralineacion4(View view) {
        onBackPressed();
        consultaEleccion(portero.get(n4));
    }

    private void consultaEleccion(int eleccion){
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        Cursor cursor2 =
                db.query(EstructuraBBDD.Jugador.TABLE_NAME_JUGADOR, null,
                        null, null, null, null, null);
        cursor2.moveToFirst();
        String posicion = cursor2.getString(2);
        String equipo = cursor2.getString(3);
        int foto = cursor2.getInt(5);
        while(foto!=eleccion){
            cursor2.moveToNext();
            posicion = cursor2.getString(2);
            equipo = cursor2.getString(3);
            foto = cursor2.getInt(5);

        }
        if(foto==eleccion){
            ContentValues values2 = new ContentValues();
            values2.put("posicion", posicion);
            values2.put("equipo", equipo);
            values2.put("foto", foto);
            db.insert("titulares",null,values2);
            db.delete("jugador","foto=="+foto,null);
        }
        db.close();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButton55:
                volveralineacion(null);
                break;
            case R.id.imageButton56:
                volveralineacion2(null);
                break;
            case R.id.imageButton57:
                volveralineacion3(null);
                break;
            case R.id.imageButton58:
                volveralineacion4(null);
                break;
        }
    }
}