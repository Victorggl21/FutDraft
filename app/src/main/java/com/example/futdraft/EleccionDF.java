package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class EleccionDF extends AppCompatActivity implements View.OnClickListener {
    SQLiteHelper helper;
    SQLiteDatabase db;

    int [] defensa = new int []{
            R.drawable.carta1k3,R.drawable.carta1k4,R.drawable.carta1k5,
            R.drawable.cartaaniaquiladores3,R.drawable.cartaaniaquiladores4,R.drawable.cartaaniaquiladores5,
            R.drawable.cartabarrio9,
            R.drawable.cartajijantes3,R.drawable.cartajijantes4,R.drawable.cartajijantes5,R.drawable.cartajijantes6,
            R.drawable.cartakuni6,R.drawable.cartakuni7,
            R.drawable.cartapio6,R.drawable.cartapio7,R.drawable.cartapio8,
            R.drawable.cartaporcinos6,R.drawable.cartaporcinos7,R.drawable.cartaporcinos8,R.drawable.cartaporcinos9,
            R.drawable.cartarayo5,R.drawable.cartarayo6,R.drawable.cartarayo7,
            R.drawable.cartasaiyans8,R.drawable.cartasaiyans9,
            R.drawable.cartatroncos3,
            R.drawable.cartaum5,R.drawable.cartaum6,
            R.drawable.cartaxbuyer3,
    };

    int n1=0,n2=0,n3=0,n4=0;
    Random random = new Random();
    Random random2 = new Random();
    Random random3 = new Random();
    Random random4 = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_df);
        jugadores();
    }

    public void jugadores(){
        n1 = random.nextInt(defensa.length);
        n2 = random2.nextInt(defensa.length);
        while(defensa[n2]==defensa[n1] ){
            n2 = random.nextInt(defensa.length);
        }
        n3 = random3.nextInt(defensa.length);
        while(defensa[n3]==defensa[n2] || defensa[n3]==defensa[n1] ){
            n3 = random.nextInt(defensa.length);
        }
        n4 = random4.nextInt(defensa.length);
        while(defensa[n4]==defensa[n3] || defensa[n4]==defensa[n2] || defensa[n4]==defensa[n1] ){
            n4 = random.nextInt(defensa.length);
        }

        ImageView img1 = findViewById(R.id.imageButton51);
        img1.setImageResource(defensa[n1]);
        ImageView img2 = findViewById(R.id.imageButton52);
        img2.setImageResource(defensa[n2]);
        ImageView img3 = findViewById(R.id.imageButton53);
        img3.setImageResource(defensa[n3]);
        ImageView img4 = findViewById(R.id.imageButton54);
        img4.setImageResource(defensa[n4]);
    }

    public void volveralineacion(View view) {
        onBackPressed();
        consultaEleccion(defensa[n1]);
    }
    public void volveralineacion2(View view) {
        onBackPressed();
        consultaEleccion(defensa[n2]);
    }
    public void volveralineacion3(View view) {
        onBackPressed();
        consultaEleccion(defensa[n3]);
    }
    public void volveralineacion4(View view) {
        onBackPressed();
        consultaEleccion(defensa[n4]);
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
        }
        db.close();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButton51:
                volveralineacion(null);
                break;
            case R.id.imageButton52:
                volveralineacion2(null);
                break;
            case R.id.imageButton53:
                volveralineacion3(null);
                break;
            case R.id.imageButton54:
                volveralineacion4(null);
                break;

        }
    }
}