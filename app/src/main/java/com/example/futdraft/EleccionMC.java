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

public class EleccionMC extends AppCompatActivity  implements View.OnClickListener {

    SQLiteHelper helper;
    SQLiteDatabase db;

    ArrayList<Integer> medios = new ArrayList<>() ;
            /*new int []{
            R.drawable.carta1k6,R.drawable.carta1k7,R.drawable.carta1k8,
            R.drawable.cartaaniaquiladores6,R.drawable.cartaaniaquiladores7,R.drawable.cartaaniaquiladores8,
            R.drawable.cartabarrio4,R.drawable.cartabarrio5,R.drawable.cartabarrio6,R.drawable.cartabarrio7,R.drawable.cartabarrio8,
            R.drawable.cartajijantes7,R.drawable.cartajijantes8,R.drawable.cartajijantes9,
            R.drawable.cartakuni2,R.drawable.cartakuni3,R.drawable.cartakuni4,R.drawable.cartakuni5,
            R.drawable.cartapio3,R.drawable.cartapio4,R.drawable.cartapio5,
            R.drawable.cartaporcinos3,R.drawable.cartaporcinos4,R.drawable.cartaporcinos5,
            R.drawable.cartarayo8,
            R.drawable.cartasaiyans1,R.drawable.cartasaiyans2,R.drawable.cartasaiyans3,R.drawable.cartasaiyans4,
            R.drawable.cartatroncos4,R.drawable.cartatroncos5,R.drawable.cartatroncos6,
            R.drawable.cartaum2,R.drawable.cartaum3,R.drawable.cartaum4,
            R.drawable.cartaxbuyer4,R.drawable.cartaxbuyer5,R.drawable.cartaxbuyer6
    }*/;


    int n1=0,n2=0,n3=0,n4=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_mc);


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
            if (posicion.equals("MC")) {
                medios.add(foto);
            }
            cursor.moveToNext();
        }
        db.close();
        do {
            n1 = random.nextInt(medios.size());
            n2 = random.nextInt(medios.size());
            n3 = random.nextInt(medios.size());
            n4 = random.nextInt(medios.size());
        } while (n1 == n2 || n1 == n3 || n1 == n4 || n2 == n3 || n2 == n4 || n3 == n4);

        /*n1 = random.nextInt(medios.size());
        n2 = random.nextInt(medios.size());
        while(medios.get(n2)==medios.get(n1) ){
            n2 = random.nextInt(medios.size());
        }
        n3 = random.nextInt(medios.size());
        while(medios.get(n3)==medios.get(n2) || medios.get(n3)==medios.get(n1) ){
            n3 = random.nextInt(medios.size());
        }
        n4 = random.nextInt(medios.size());
        while(medios.get(n4)==medios.get(n3) || medios.get(n4)==medios.get(n2) || medios.get(n4)==medios.get(n1) ){
            n4 = random.nextInt(medios.size());
        }*/

        ImageView img1 = findViewById(R.id.imageButton47);
        img1.setImageResource(medios.get(n1));
        ImageView img2 = findViewById(R.id.imageButton48);
        img2.setImageResource(medios.get(n2));
        ImageView img3 = findViewById(R.id.imageButton49);
        img3.setImageResource(medios.get(n3));
        ImageView img4 = findViewById(R.id.imageButton50);
        img4.setImageResource(medios.get(n4));
    }

    public void volveralineacion(View view) {
        onBackPressed();
        consultaEleccion(medios.get(n1));
    }
    public void volveralineacion2(View view) {
        onBackPressed();
        consultaEleccion(medios.get(n2));
    }
    public void volveralineacion3(View view) {
        onBackPressed();
        consultaEleccion(medios.get(n3));
    }
    public void volveralineacion4(View view) {
        onBackPressed();
        consultaEleccion(medios.get(n4));
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
        switch(view.getId()){
            case R.id.imageButton47:
                volveralineacion(null);
                break;
            case R.id.imageButton48:
                volveralineacion2(null);
                break;
            case R.id.imageButton49:
                volveralineacion3(null);
                break;
            case R.id.imageButton50:
                volveralineacion4(null);
                break;
        }
    }
}